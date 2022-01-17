package com.ifmo.isdb.strattanoakmant.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Client extends Person {
    @Column(name = "type")
    private String type;
}
