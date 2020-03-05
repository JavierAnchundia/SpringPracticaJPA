package com.maint.maintjpa.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public abstract class EntidadBase {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    public boolean isPersisted() {
        return id != null;
    }
}

