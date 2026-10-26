# Vehiculos

Aplicacion de consola en Java para gestionar una flota de vehiculos (coches, motos y autobuses). Proyecto de practica centrado en programacion orientada a objetos.

## Que demuestra

- **Herencia y clase abstracta:** `Vehiculo` (abstracta) con `Coche`, `Moto` y `Autobus`.
- **Polimorfismo:** una unica `List<Vehiculo>` con recuento por tipo mediante `Class.isInstance`.
- **Enums con valor:** `CilindradaEnum` (cc) y `PlazasEnum` (plazas), ademas de color, motor y ruta.
- **Excepciones propias:** `VelocidadInvalida` y `MatriculaDuplicada`, tratadas con `try/catch` en la vista.
- **Sobrecarga de metodos:** `acelerar()` / `acelerar(float)` y `frenar()` / `frenar(float)`.
- **API de fechas moderna:** `LocalDate`, validacion estricta (anyos bisiestos incluidos) y calculo de antiguedad con `Period`.
- **Entrada robusta:** un unico `Scanner`, lectura con `nextLine()` y reintento ante datos invalidos.

## Funcionalidades

1. Crear coche, moto o autobus (la matricula no puede repetirse).
2. Listar matriculas y buscar un vehiculo por matricula.
3. Contar vehiculos totales y por tipo.
4. Eliminar un vehiculo.
5. Acelerar y frenar (con o sin cantidad); limite de 120 km/h.
6. Calcular la antiguedad del vehiculo.

## Estructura

```
src/com/alcrudov/vehiculos/
├── model/        Vehiculo, Coche, Moto, Autobus y enums
├── exceptions/   VelocidadInvalida, MatriculaDuplicada
├── util/         Entrada (lectura por consola)
└── view/         App (menu principal)
```

## Ejecucion

Requiere JDK 17 o superior.

```bash
mkdir out
javac -d out $(find src -name "*.java")
java -cp out com.alcrudov.vehiculos.view.App
```

En NetBeans / IntelliJ: crea un proyecto Java, copia la carpeta `src` y ejecuta `App`.

## Autor

Victor Alcrudo Abad — [GitHub](https://github.com/Krudoner)
