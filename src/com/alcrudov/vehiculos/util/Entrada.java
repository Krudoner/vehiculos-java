package com.alcrudov.vehiculos.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

/**
 * Lectura de datos por consola. Usa un unico Scanner y siempre nextLine(),
 * asi no se pierden saltos de linea ni salta InputMismatchException.
 */
public final class Entrada {

    private static final Scanner SC = new Scanner(System.in);
    private static final DateTimeFormatter FORMATO_FECHA
            = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private Entrada() {
    }

    public static String leerTexto(String campo) {
        while (true) {
            System.out.print(campo + ": ");
            String texto = SC.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("ERROR: El campo no puede estar vacio.");
        }
    }

    public static int leerEntero(String campo, int min, int max) {
        while (true) {
            System.out.print(campo + ": ");
            try {
                int valor = Integer.parseInt(SC.nextLine().trim());
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("ERROR: Introduce un numero entre " + min + " y " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Introduce un numero entero.");
            }
        }
    }

    public static float leerPositivo(String campo) {
        while (true) {
            System.out.print(campo + ": ");
            try {
                float valor = Float.parseFloat(SC.nextLine().trim().replace(',', '.'));
                if (valor > 0) {
                    return valor;
                }
                System.out.println("ERROR: El valor debe ser mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Introduce un numero valido.");
            }
        }
    }

    /** Lee una fecha dd/mm/aaaa valida (con bisiestos) y que no sea futura. */
    public static LocalDate leerFecha(String campo) {
        while (true) {
            System.out.print(campo + " (dd/mm/aaaa): ");
            try {
                LocalDate fecha = LocalDate.parse(SC.nextLine().trim(), FORMATO_FECHA);
                if (fecha.isAfter(LocalDate.now())) {
                    System.out.println("ERROR: La fecha no puede ser futura.");
                } else if (fecha.getYear() < 1900) {
                    System.out.println("ERROR: El anyo debe ser 1900 o posterior.");
                } else {
                    return fecha;
                }
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: Formato o valores incorrectos.");
            }
        }
    }

    /** Muestra las constantes de un enum numeradas y devuelve la elegida. */
    public static <E extends Enum<E>> E leerEnum(String titulo, Class<E> clase) {
        E[] valores = clase.getEnumConstants();
        System.out.println(titulo + ":");
        for (int i = 0; i < valores.length; i++) {
            System.out.println((i + 1) + ". " + valores[i]);
        }
        return valores[leerEntero("Opcion", 1, valores.length) - 1];
    }
}
