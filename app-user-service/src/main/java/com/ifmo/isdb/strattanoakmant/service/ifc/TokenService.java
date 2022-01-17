package com.ifmo.isdb.strattanoakmant.service.ifc;

import com.ifmo.isdb.strattanoakmant.model.Employee;
import com.ifmo.isdb.strattanoakmant.model.JwtToken;
import com.ifmo.isdb.strattanoakmant.model.Login;

public interface TokenService {
    JwtToken createToken(Login login);
    Employee getUserByToken(String token);
    String getRoleByToken(String token);
}
