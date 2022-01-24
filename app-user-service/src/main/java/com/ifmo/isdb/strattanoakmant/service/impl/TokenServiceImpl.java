package com.ifmo.isdb.strattanoakmant.service.impl;

import com.ifmo.isdb.strattanoakmant.model.Employee;
import com.ifmo.isdb.strattanoakmant.model.JwtToken;
import com.ifmo.isdb.strattanoakmant.model.Login;
import com.ifmo.isdb.strattanoakmant.repository.EmployeeRepository;
import com.ifmo.isdb.strattanoakmant.repository.PositionRepository;
import com.ifmo.isdb.strattanoakmant.repository.TokenRepository;
import com.ifmo.isdb.strattanoakmant.service.ifc.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private static final Logger log =
            LoggerFactory.getLogger(TokenServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final TokenRepository tokenRepository;
    private final CacheManager cacheManager;


    @Override
    @Cacheable(value = "token", key = "#login.login")
    public JwtToken createToken(Login login) {
        log.debug(String.format("Creating token for user = %s", login.getLogin()));
        Employee employee = Optional
                .ofNullable(employeeRepository.findByLoginAndPassword(login.getLogin(), login.getPassword()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Can't find employee by login = %s ", login.getLogin())));
        String role = Optional.ofNullable(positionRepository.findRoleById(employee.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Can't find role by employee id= %s ", employee.getId())));
        JwtToken token = createToken(employee.getId(), role);
        log.debug(String.format("Created token for user = %s", login.getLogin()));
        return tokenRepository.save(token);
    }

    public Employee getUserByToken(String token) {
        log.debug(String.format("Finding user by token = %s", token));
        Map<String, Object> claims = getClaims(token);
        Integer userId = (Integer) claims.get("uid");
        return employeeRepository
                .findById(new Long(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Bad token, no employee for id = %s ", userId)));
    }

    public String getRoleByToken(String token) {
        log.debug(String.format("Finding role by token = %s", token));
        Map<String, Object> claims = getClaims(token);
        return (String) claims.get("role");
    }

    private DefaultClaims getClaims(String token) {
        return (DefaultClaims) Jwts.parserBuilder()
                .build()
                .parse(token)
                .getBody();
    }

    private JwtToken createToken(Long userId, String role) {
        LocalDateTime dateTime = LocalDateTime.now();
        Instant issuedDateInstant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
        Instant expirationDateInstant = dateTime.plusHours(12).atZone(ZoneId.systemDefault()).toInstant();

        Map<String, Object> customHeader = new HashMap<>();
        customHeader.put("type", "JWT");

        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("uid", userId);
        customClaims.put("role", role);


        String compact = Jwts.builder()
                .setHeader(customHeader)
                .addClaims(customClaims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(issuedDateInstant))
                .setExpiration(Date.from(expirationDateInstant))
                .compact();

        return new JwtToken(compact, role, LocalDateTime.now());
    }

    @Scheduled(cron = "*/10 * * * * ?")
    public void refreshTokens() {
        List<JwtToken> all = tokenRepository.findAll();
        for (JwtToken token :
                all) {
            if (token.getLocalDateTime().plusHours(1).isBefore(LocalDateTime.now())) {
                tokenRepository.delete(token);
            }
        }
    }

    @Scheduled(fixedRate = 10000)
    public void evictTokenAtIntervals() {
        Objects.requireNonNull(cacheManager.getCache("token")).clear();
    }
}
