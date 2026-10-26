package com.alcrudov.vehiculos.model;

/** Capacidades disponibles para un autobus, con su numero de plazas. */
public enum PlazasEnum {
    PL_20(20),
    PL_30(30),
    PL_40(40),
    PL_50(50),
    PL_60(60),
    PL_70(70);

    private final int plazas;

    PlazasEnum(int plazas) {
        this.plazas = plazas;
    }

    public int getPlazas() {
        return plazas;
    }

    @Override
    public String toString() {
        return plazas + " plazas";
    }
}
