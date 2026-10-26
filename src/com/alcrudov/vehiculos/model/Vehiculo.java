package com.alcrudov.vehiculos.model;

import com.alcrudov.vehiculos.exceptions.VelocidadInvalida;
import java.time.LocalDate;
import java.time.Period;

/**
 * Clase base abstracta de todos los vehiculos.
 * Guarda los datos comunes y la logica de aceleracion y frenado.
 */
public abstract class Vehiculo {

    public static final float VELOCIDAD_MAXIMA = 120.0f;
    private static final float CAMBIO_POR_DEFECTO = 10.0f;

    private String matricula;
    private ColorEnum color;
    private String marca;
    private String modelo;
    private TipoMotorEnum tipoMotor;
    private float velocidad;
    private LocalDate fechaFabricacion;

    protected Vehiculo(String matricula, ColorEnum color, String marca, String modelo,
            TipoMotorEnum tipoMotor, LocalDate fechaFabricacion) {
        this.matricula = matricula;
        this.color = color;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.fechaFabricacion = fechaFabricacion;
        this.velocidad = 0.0f;
    }

    /** Nombre del tipo de vehiculo ("Coche", "Moto", "Autobus"). */
    public abstract String getTipo();

    // GETTERS Y SETTERS
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public ColorEnum getColor() {
        return color;
    }

    public void setColor(ColorEnum color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public TipoMotorEnum getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(TipoMotorEnum tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(LocalDate fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    /** Antiguedad del vehiculo en anyos, meses y dias. */
    public Period getAntiguedad() {
        return Period.between(fechaFabricacion, LocalDate.now());
    }

    // ACELERAR Y FRENAR
    public void acelerar() throws VelocidadInvalida {
        acelerar(CAMBIO_POR_DEFECTO);
    }

    public void acelerar(float incremento) throws VelocidadInvalida {
        if (incremento <= 0) {
            throw new VelocidadInvalida("El incremento debe ser mayor a 0.");
        }
        velocidad += incremento;
        if (velocidad > VELOCIDAD_MAXIMA) {
            velocidad = VELOCIDAD_MAXIMA;
            throw new VelocidadInvalida("Limite alcanzado. Velocidad ajustada a "
                    + VELOCIDAD_MAXIMA + " km/h.");
        }
    }

    public void frenar() throws VelocidadInvalida {
        frenar(CAMBIO_POR_DEFECTO);
    }

    public void frenar(float decremento) throws VelocidadInvalida {
        if (decremento <= 0) {
            throw new VelocidadInvalida("El decremento debe ser mayor a 0.");
        }
        if (velocidad == 0) {
            throw new VelocidadInvalida("El vehiculo ya esta parado, acelere primero.");
        }
        velocidad = Math.max(0, velocidad - decremento);
    }

    @Override
    public String toString() {
        return getTipo() + "{matricula=" + matricula
                + ", color=" + color
                + ", marca=" + marca
                + ", modelo=" + modelo
                + ", tipoMotor=" + tipoMotor
                + ", velocidad=" + velocidad + " km/h"
                + ", fechaFabricacion=" + fechaFabricacion;
    }
}
