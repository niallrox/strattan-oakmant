package com.ifmo.isdb.strattanoakmant.aspect;

import com.ifmo.isdb.strattanoakmant.model.Role;
import com.ifmo.isdb.strattanoakmant.security.AccessRole;
import com.ifmo.isdb.strattanoakmant.service.ifc.TokenService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Aspect
@Service
@RequiredArgsConstructor
public class AccessRoleAspect {

    private final TokenService tokenService;

    @Around(value = "within(@org.springframework.web.bind.annotation.RestController *) && args(token, ..)" +
            "&& !within(*..TokenController) && !within(springfox..*)")
    public Object roleAccess(ProceedingJoinPoint joinPoint, String token) {
        try {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            String roleByToken = tokenService.getRoleByToken(token);
            AccessRole methodAnnotation = method.getAnnotation(AccessRole.class);
            AccessRole classAnnotation = method.getDeclaringClass().getAnnotation(AccessRole.class);
            List<String> rolesOnClass = Objects.nonNull(classAnnotation) ? Arrays
                    .stream(classAnnotation.value())
                    .map(Role::getName)
                    .collect(Collectors.toList()) : new ArrayList<>();
            if (Objects.nonNull(methodAnnotation)) {
                List<String> rolesOnMethod = Arrays
                        .stream(methodAnnotation.value())
                        .map(Role::getName)
                        .collect(Collectors.toList());
                rolesOnMethod.addAll(rolesOnClass);
                return isRoleInList(joinPoint, roleByToken, rolesOnMethod);
            } else if (!rolesOnClass.isEmpty()) {
                return isRoleInList(joinPoint, roleByToken, rolesOnClass);
            } else {
                return joinPoint.proceed();
            }
        } catch (Throwable e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid token");
        }
    }

    private Object isRoleInList(ProceedingJoinPoint joinPoint, String
            mainRole, List<String> collectOfAnnotationArgs)
            throws Throwable {
        if (collectOfAnnotationArgs.stream().anyMatch(s -> s.equalsIgnoreCase(mainRole))) {
            return joinPoint.proceed();
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not enough Rights");
        }
    }
}
