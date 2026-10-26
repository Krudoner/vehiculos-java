package com.alcrudov.vehiculos.model;

import java.time.LocalDate;

public class Autobus extends Vehiculo {

    private PlazasEnum plazas;
    private RutaEnum ruta;

    public Autobus(PlazasEnum plazas, RutaEnum ruta, String matricula, ColorEnum color,
            String marca, String modelo, TipoMotorEnum tipoMotor, LocalDate fechaFabricacion) {
        super(matricula, color, marca, modelo, tipoMotor, fechaFabricacion);
        this.plazas = plazas;
        this.ruta = ruta;
    }

    public PlazasEnum getPlazas() {
        return plazas;
    }

    public void setPlazas(PlazasEnum plazas) {
        this.plazas = plazas;
    }

    public RutaEnum getRuta() {
        return ruta;
    }

    public void setRuta(RutaEnum ruta) {
        this.ruta = ruta;
    }

    @Override
    public String getTipo() {
        return "Autobus";
    }

    @Override
    public String toString() {
        return super.toString() + ", plazas=" + plazas + ", ruta=" + ruta + "}";
    }
}
