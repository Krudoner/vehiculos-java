package com.alcrudov.vehiculos.exceptions;

/** Se lanza cuando se intenta registrar una matricula que ya existe. */
public class MatriculaDuplicada extends Exception {

    public MatriculaDuplicada(String matricula) {
        super("La matricula " + matricula + " ya existe en el sistema.");
    }
}
