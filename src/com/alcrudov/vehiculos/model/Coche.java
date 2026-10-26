package com.alcrudov.vehiculos.model;

import java.time.LocalDate;

public class Coche extends Vehiculo {

    private int puertas;

    public Coche(int puertas, String matricula, ColorEnum color, String marca, String modelo,
            TipoMotorEnum tipoMotor, LocalDate fechaFabricacion) {
        super(matricula, color, marca, modelo, tipoMotor, fechaFabricacion);
        this.puertas = puertas;
    }

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    @Override
    public String getTipo() {
        return "Coche";
    }

    @Override
    public String toString() {
        return super.toString() + ", puertas=" + puertas + "}";
    }
}
