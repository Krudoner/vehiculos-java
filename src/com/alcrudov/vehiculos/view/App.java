package com.alcrudov.vehiculos.view;

import com.alcrudov.vehiculos.exceptions.MatriculaDuplicada;
import com.alcrudov.vehiculos.exceptions.VelocidadInvalida;
import com.alcrudov.vehiculos.model.Autobus;
import com.alcrudov.vehiculos.model.CilindradaEnum;
import com.alcrudov.vehiculos.model.Coche;
import com.alcrudov.vehiculos.model.ColorEnum;
import com.alcrudov.vehiculos.model.Moto;
import com.alcrudov.vehiculos.model.PlazasEnum;
import com.alcrudov.vehiculos.model.RutaEnum;
import com.alcrudov.vehiculos.model.TipoMotorEnum;
import com.alcrudov.vehiculos.model.Vehiculo;
import com.alcrudov.vehiculos.util.Entrada;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/** Menu de consola para gestionar una lista de vehiculos. */
public class App {

    private static final List<Vehiculo> vehiculos = new ArrayList<>();

    /** Datos comunes a todos los vehiculos. */
    private record DatosComunes(String matricula, String marca, String modelo,
            LocalDate fecha, TipoMotorEnum motor, ColorEnum color) {

    }

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = Entrada.leerEntero("\nSelecciona una opcion", 0, 15);

            if (opcion >= 4 && vehiculos.isEmpty()) {
                System.out.println("Primero crea algun vehiculo.");
                continue;
            }

            switch (opcion) {
                case 1 -> crearCoche();
                case 2 -> crearMoto();
                case 3 -> crearAutobus();
                case 4 -> mostrarMatriculas();
                case 5 -> buscarPorMatricula();
                case 6 -> System.out.println("\nTotal de vehiculos: " + vehiculos.size());
                case 7 -> System.out.println("\nTotal de coches: " + contar(Coche.class));
                case 8 -> System.out.println("\nTotal de motos: " + contar(Moto.class));
                case 9 -> System.out.println("\nTotal de autobuses: " + contar(Autobus.class));
                case 10 -> borrarVehiculo();
                case 11 -> acelerar(false);
                case 12 -> acelerar(true);
                case 13 -> frenar(false);
                case 14 -> frenar(true);
                case 15 -> mostrarAntiguedad();
                case 0 -> System.out.println("Saliendo del programa...");
                default -> {
                }
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n--- GESTION DE VEHICULOS ---");
        System.out.println("1. Crear coche");
        System.out.println("2. Crear moto");
        System.out.println("3. Crear autobus");
        if (!vehiculos.isEmpty()) {
            System.out.println("4. Mostrar todas las matriculas");
            System.out.println("5. Buscar vehiculo por matricula");
            System.out.println("6. Mostrar total de vehiculos");
            System.out.println("7. Mostrar total de coches");
            System.out.println("8. Mostrar total de motos");
            System.out.println("9. Mostrar total de autobuses");
            System.out.println("10. Eliminar vehiculo por matricula");
            System.out.println("11. Acelerar");
            System.out.println("12. Acelerar con parametros");
            System.out.println("13. Frenar");
            System.out.println("14. Frenar con parametros");
            System.out.println("15. Calcular antiguedad");
        }
        System.out.println("0. Salir");
    }

    // CREACION
    private static DatosComunes leerDatosComunes() {
        String matricula = pedirMatriculaNueva();
        String marca = Entrada.leerTexto("Marca");
        String modelo = Entrada.leerTexto("Modelo");
        LocalDate fecha = Entrada.leerFecha("Fecha de fabricacion");
        TipoMotorEnum motor = Entrada.leerEnum("Selecciona un motor", TipoMotorEnum.class);
        ColorEnum color = Entrada.leerEnum("Selecciona un color", ColorEnum.class);
        return new DatosComunes(matricula, marca, modelo, fecha, motor, color);
    }

    private static void crearCoche() {
        System.out.println("\n--- NUEVO COCHE ---");
        DatosComunes d = leerDatosComunes();
        int puertas = Entrada.leerEntero("Numero de puertas", 2, 6);
        vehiculos.add(new Coche(puertas, d.matricula(), d.color(), d.marca(), d.modelo(),
                d.motor(), d.fecha()));
        System.out.println("Coche anyadido correctamente.");
    }

    private static void crearMoto() {
        System.out.println("\n--- NUEVA MOTO ---");
        DatosComunes d = leerDatosComunes();
        CilindradaEnum cilindrada = Entrada.leerEnum("Selecciona la cilindrada", CilindradaEnum.class);
        vehiculos.add(new Moto(cilindrada, d.matricula(), d.color(), d.marca(), d.modelo(),
                d.motor(), d.fecha()));
        System.out.println("Moto anyadida correctamente.");
    }

    private static void crearAutobus() {
        System.out.println("\n--- NUEVO AUTOBUS ---");
        DatosComunes d = leerDatosComunes();
        PlazasEnum plazas = Entrada.leerEnum("Selecciona la capacidad", PlazasEnum.class);
        RutaEnum ruta = Entrada.leerEnum("Selecciona el tipo de ruta", RutaEnum.class);
        vehiculos.add(new Autobus(plazas, ruta, d.matricula(), d.color(), d.marca(), d.modelo(),
                d.motor(), d.fecha()));
        System.out.println("Autobus anyadido correctamente.");
    }

    // MATRICULAS
    private static Vehiculo buscarVehiculo(String matricula) {
        for (Vehiculo v : vehiculos) {
            if (v.getMatricula().equalsIgnoreCase(matricula)) {
                return v;
            }
        }
        return null;
    }

    private static void comprobarMatriculaLibre(String matricula) throws MatriculaDuplicada {
        if (buscarVehiculo(matricula) != null) {
            throw new MatriculaDuplicada(matricula);
        }
    }

    private static String pedirMatriculaNueva() {
        while (true) {
            String matricula = Entrada.leerTexto("Matricula").toUpperCase();
            try {
                comprobarMatriculaLibre(matricula);
                return matricula;
            } catch (MatriculaDuplicada e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    /** Pide una matricula y devuelve el vehiculo, o null si no existe. */
    private static Vehiculo pedirVehiculoExistente() {
        Vehiculo v = buscarVehiculo(Entrada.leerTexto("Matricula"));
        if (v == null) {
            System.out.println("Matricula inexistente.\n");
        }
        return v;
    }

    // CONSULTAS
    private static void mostrarMatriculas() {
        for (int i = 0; i < vehiculos.size(); i++) {
            Vehiculo v = vehiculos.get(i);
            System.out.println((i + 1) + " - " + v.getTipo() + ": " + v.getMatricula());
        }
    }

    private static void buscarPorMatricula() {
        Vehiculo v = pedirVehiculoExistente();
        if (v != null) {
            System.out.println(v);
        }
    }

    private static int contar(Class<? extends Vehiculo> tipo) {
        int total = 0;
        for (Vehiculo v : vehiculos) {
            if (tipo.isInstance(v)) {
                total++;
            }
        }
        return total;
    }

    private static void borrarVehiculo() {
        Vehiculo v = pedirVehiculoExistente();
        if (v != null) {
            vehiculos.remove(v);
            System.out.println("Vehiculo " + v.getMatricula() + " borrado correctamente.");
        }
    }

    private static void mostrarAntiguedad() {
        Vehiculo v = pedirVehiculoExistente();
        if (v != null) {
            Period p = v.getAntiguedad();
            System.out.println("La antiguedad del vehiculo es de " + p.getYears()
                    + " anyos y " + p.getMonths() + " meses.\n");
        }
    }

    // ACELERAR Y FRENAR
    private static void acelerar(boolean conParametro) {
        Vehiculo v = pedirVehiculoExistente();
        if (v == null) {
            return;
        }
        try {
            if (conParametro) {
                v.acelerar(Entrada.leerPositivo("Cuantos km/h quieres aumentar"));
            } else {
                v.acelerar();
            }
            System.out.println(v.getTipo() + " acelerando. Velocidad actual: "
                    + v.getVelocidad() + " km/h\n");
        } catch (VelocidadInvalida e) {
            System.out.println("AVISO: " + e.getMessage() + "\n");
        }
    }

    private static void frenar(boolean conParametro) {
        Vehiculo v = pedirVehiculoExistente();
        if (v == null) {
            return;
        }
        try {
            if (conParametro) {
                v.frenar(Entrada.leerPositivo("Cuantos km/h quieres disminuir"));
            } else {
                v.frenar();
            }
            System.out.println(v.getTipo() + " frenando. Velocidad actual: "
                    + v.getVelocidad() + " km/h\n");
        } catch (VelocidadInvalida e) {
            System.out.println("AVISO: " + e.getMessage() + "\n");
        }
    }
}
