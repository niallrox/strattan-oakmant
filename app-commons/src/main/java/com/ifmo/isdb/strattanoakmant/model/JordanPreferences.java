package com.ifmo.isdb.strattanoakmant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JordanPreferences {
    COCAINE("COCAINE"),
    GONG("GONG"),
    WHISKEY("WHISKEY");

    private String name;
}
