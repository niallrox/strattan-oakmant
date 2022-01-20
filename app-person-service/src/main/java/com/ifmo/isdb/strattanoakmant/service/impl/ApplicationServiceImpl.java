package com.ifmo.isdb.strattanoakmant.service.impl;

import com.ifmo.isdb.strattanoakmant.model.Application;
import com.ifmo.isdb.strattanoakmant.model.Person;
import com.ifmo.isdb.strattanoakmant.repository.ApplicationRepository;
import com.ifmo.isdb.strattanoakmant.repository.PersonRepository;
import com.ifmo.isdb.strattanoakmant.service.ifc.ApplicationService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private static final Logger log =
            LoggerFactory.getLogger(ApplicationServiceImpl.class);

    private final ApplicationRepository applicationRepository;

    private static final String REFUSAL_RESPONSE = "Да и пошел он!";
    private static final String ACCEPT_RESPONSE = "Одобрено! У вас новый сотрудник";

    @Override
    public List<Application> getApplications() {
        log.debug("Finding applications...");
        return applicationRepository.findAll();
    }

    @Override
    public String deleteApplication(Long id, boolean reason) {
        log.debug(String.format("Deleting application with id = %s", id));
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Can't find application by id = %s ", id)));
        applicationRepository.delete(application);
        return reason ? ACCEPT_RESPONSE : REFUSAL_RESPONSE;
    }

    @Override
    public Application saveApplication(Application application) {
        log.debug(String.format("Saving application by name = %s", application.getName()));
        return applicationRepository.save(application);
    }
}
