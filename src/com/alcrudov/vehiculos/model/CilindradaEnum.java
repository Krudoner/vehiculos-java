package com.alcrudov.vehiculos.model;

/** Cilindradas disponibles para una moto, con su valor numerico en cc. */
public enum CilindradaEnum {
    CLI_49CC(49),
    CLI_125CC(125),
    CLI_250CC(250),
    CLI_600CC(600),
    CLI_1200CC(1200);

    private final int cc;

    CilindradaEnum(int cc) {
        this.cc = cc;
    }

    public int getCc() {
        return cc;
    }

    @Override
    public String toString() {
        return cc + " cc";
    }
}
