package com.ifmo.isdb.strattanoakmant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    HR("HR"),
    SELLER("SELLER");

    private String name;
}
