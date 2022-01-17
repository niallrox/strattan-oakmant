package com.ifmo.isdb.strattanoakmant.security;

import com.ifmo.isdb.strattanoakmant.model.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessRole {
    Role[] value();
}
