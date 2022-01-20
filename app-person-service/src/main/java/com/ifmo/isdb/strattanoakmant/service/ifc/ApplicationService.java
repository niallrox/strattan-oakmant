package com.ifmo.isdb.strattanoakmant.service.ifc;

import com.ifmo.isdb.strattanoakmant.model.Application;

import java.util.List;

public interface ApplicationService {
    List<Application> getApplications();
    String deleteApplication(Long id, boolean reason);
    Application saveApplication(Application application);
}
