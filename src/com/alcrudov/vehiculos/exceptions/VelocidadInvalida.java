package com.alcrudov.vehiculos.exceptions;

/** Se lanza cuando una operacion de aceleracion o frenado no es valida. */
public class VelocidadInvalida extends Exception {

    public VelocidadInvalida(String mensaje) {
        super(mensaje);
    }
}
