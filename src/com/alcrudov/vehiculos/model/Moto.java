package com.alcrudov.vehiculos.model;

import java.time.LocalDate;

public class Moto extends Vehiculo {

    private CilindradaEnum cilindrada;

    public Moto(CilindradaEnum cilindrada, String matricula, ColorEnum color, String marca,
            String modelo, TipoMotorEnum tipoMotor, LocalDate fechaFabricacion) {
        super(matricula, color, marca, modelo, tipoMotor, fechaFabricacion);
        this.cilindrada = cilindrada;
    }

    public CilindradaEnum getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(CilindradaEnum cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public String getTipo() {
        return "Moto";
    }

    @Override
    public String toString() {
        return super.toString() + ", cilindrada=" + cilindrada + "}";
    }
}
