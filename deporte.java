
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class deporte {
    private Scanner nagj = new Scanner(System.in);
    private String[] pelotas = {"fútbol", "baloncesto", "voley", "tenis"};
    private double[] precioPelotas = {50.00, 60.00, 40.00, 30.00};

    private String[] camisetas = {"camiseta de fútbol", "camiseta de baloncesto", "camiseta de voley", "camiseta de tenis"};
    private double[] precioCamisetas = {70.00, 80.00, 60.00, 50.00};

    private String[] zapatillas = {"zapatillas de fútbol", "zapatillas de baloncesto", "zapatillas de voley", "zapatillas de tenis"};
    private double[] precioZapatillas = {150.00, 140.00, 130.00, 120.00};

    private String[] equipos = {"guantes de portero", "red de voleibol", "raqueta de tenis", "balón de baloncesto"};
    private double[] precioEquipos = {40.00, 100.00, 150.00, 60.00};

    private int[] carritoPelotas = new int[pelotas.length];
    private int[] carritoCamisetas = new int[camisetas.length];
    private int[] carritoZapatillas = new int[zapatillas.length];
    private int[] carritoEquipos = new int[equipos.length];




    private String numeroTarjeta;
    private String nombreTitular;
    private String fechaExpiracion;
    private String cvv;
    private String metodoPago;


    public static void main(String[] args) {
        deporte pio = new deporte();
        pio.iniciar();
    }

    public void iniciar() {
        iniciarSesion();
        menuPrincipal();
    }

    private void iniciarSesion() {
        String[] credenciales = {"heber", "2003", "54321"};
        System.out.println("*** SPORT PUMA ***");
        System.out.println("Ingrese su usuario:");
        String usuario = nagj.nextLine();
        System.out.println("Ingrese su contraseña:");
        String contraseña = nagj.nextLine();

        if (usuario.equals(credenciales[1]) && contraseña.equals(credenciales[2])) {
            System.out.println("Bienvenido " + credenciales[0]);
        } else {
            System.out.println("Usuario/Contraseña no válidos");
            iniciarSesion();
        }
    }

    public void menuPrincipal() {
        System.out.println("---------TIENDA SPORT PUMA -----------");
        System.out.println("Seleccione una de las siguientes opciones:");
        System.out.println("1. Pelotas");
        System.out.println("2. Camisetas");
        System.out.println("3. Zapatillas");
        System.out.println("4. Equipos");
        System.out.println("5. Libro de Reclamos");

        int opcion = nagj.nextInt();
        nagj.nextLine();

        switch (opcion) {
            case 1:
                comprarPelotas();
                break;
            case 2:
                comprarCamisetas();
                break;
            case 3:
                comprarZapatillas();
                break;
            case 4:
                comprarEquipos();
                break;
            case 5:
                libroDeReclamos();
                break;
            default: {
                System.out.println("Opción no válida. Intente de nuevo.");
                menuPrincipal();
            }
        }
    }

    private void comprarPelotas() {
        System.out.println("** Pelotas ***");
        seleccionarYComprar(pelotas, precioPelotas, carritoPelotas);
    }

    private void comprarCamisetas() {
        System.out.println("** Camisetas ***");
        seleccionarYComprar(camisetas, precioCamisetas, carritoCamisetas);
    }

    private void comprarZapatillas() {
        System.out.println("** Zapatillas ***");
        seleccionarYComprar(zapatillas, precioZapatillas, carritoZapatillas);
    }

    private void comprarEquipos() {
        System.out.println("** Equipos ***");
        seleccionarYComprar(equipos, precioEquipos, carritoEquipos);
    }

    private void seleccionarYComprar(String[] productos, double[] precios, int[] carrito) {
        for (int i = 0; i < productos.length; i++) {
            System.out.println("Opción " + (i + 1) + ": " + productos[i]);
        }

        int opcion = nagj.nextInt();
        nagj.nextLine();

        if (opcion < 1 || opcion > productos.length) {
            System.out.println("Opción no válida. Intente de nuevo.");
            seleccionarYComprar(productos, precios, carrito);
            return;
        }

        System.out.println("¿Cuántos comprará? Ingrese la cantidad:");
        int cantidad = nagj.nextInt();
        carrito[opcion - 1] += cantidad;

        System.out.println("Ud ha pedido " + carrito[opcion - 1] + " de " + productos[opcion - 1]);
        System.out.println("¿Desea elegir otro producto? 1. SI / 2. NO / 3. Menú Principal");

        opcion = nagj.nextInt();
        nagj.nextLine();

        switch (opcion) {
            case 1:
                seleccionarYComprar(productos, precios, carrito);
                break;
            case 2:
                metodoPago();
                calcularPago(carrito, precios, productos);
                break;
            case 3:
                menuPrincipal();
                break;
        }
    }

    private void metodoPago() {
        System.out.println("Seleccione el método de pago:");
        System.out.println("1. Tarjeta Visa");
        System.out.println("2. Efectivo");

        int opcion = nagj.nextInt();
        nagj.nextLine();

        if (opcion == 1) {
            metodoPago = "Tarjeta Visa";
            System.out.println("Ingrese el número de su tarjeta Visa:");
            numeroTarjeta = nagj.nextLine();
            System.out.println("Ingrese el nombre del titular de la tarjeta:");
            nombreTitular = nagj.nextLine();
            System.out.println("Ingrese la fecha de expiración (MM/AA):");
            fechaExpiracion = nagj.nextLine();
            System.out.println("Ingrese el CVV:");
            cvv = nagj.nextLine();
        } else if (opcion == 2) {
            metodoPago = "Efectivo";
        } else {
            System.out.println("Opción no válida. Intente de nuevo.");
            metodoPago();
        }
    }

    private boolean validarPagoTarjeta() {
        return numeroTarjeta != null && numeroTarjeta.length() == 16 &&
                nombreTitular != null && !nombreTitular.isEmpty() &&
                fechaExpiracion != null && fechaExpiracion.matches("\\d{2}/\\d{2}") &&
                cvv != null && cvv.length() == 3;
    }

    private void calcularPago(int[] carrito, double[] precios, String[] items) {
        double subtotal = 0;
        for (int i = 0; i < carrito.length; i++) {
            subtotal += carrito[i] * precios[i];
        }
        double igv = subtotal * 0.18;
        double totalPagar = subtotal + igv;

        System.out.println("---- BOLETA DE VENTA ---------");
        System.out.println("Nombre del Cliente: " );
        System.out.println("Fecha: " + new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss").format(new Date()));
        System.out.println("Productos Comprados:");
        for (int i = 0; i < carrito.length; i++) {
            if (carrito[i] > 0) {
                System.out.println(items[i] + " x " + carrito[i] + " = " + (carrito[i] * precios[i]));
            }
        }
        System.out.println("SUBTOTAL: " + subtotal);
        System.out.println("IGV: " + igv);
        System.out.println("TOTAL: " + totalPagar);
        System.out.println("Método de Pago: " + metodoPago);

        if (metodoPago.equals("Tarjeta") && validarPagoTarjeta()) {
            System.out.println("Pago realizado con éxito con Tarjeta.");
            exportarBoleta(subtotal, igv, totalPagar, metodoPago, items, carrito, 0);
        } else if (metodoPago.equals("Efectivo")) {
            System.out.println("Ingrese la cantidad de efectivo recibido:");
            double efectivoRecibido = nagj.nextDouble();
            double cambio = efectivoRecibido - totalPagar;
            System.out.println("Cambio: " + cambio);
            exportarBoleta(subtotal, igv, totalPagar, metodoPago, items, carrito, cambio);
        } else {
            System.out.println("Error en el pago con Tarjeta. Intente nuevamente.");
        }
    }

    private void exportarBoleta(double subtotal, double igv, double totalPagar, String metodoPago, String[] items, int[] carrito, double cambio) {
        try {
            File archivo = new File("D:\\boleta.txt");
            FileWriter escritor = new FileWriter(archivo);
            escritor.write("---- BOLETA DE VENTA ---------\n");
            escritor.write("Nombre del Cliente: " );
            escritor.write("Fecha: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + "\n");
            escritor.write("Productos Comprados:\n");
            for (int i = 0; i < carrito.length; i++) {
                if (carrito[i] > 0) {
                    escritor.write(items[i] + " x " + carrito[i] + " = " + (carrito[i] * precioCamisetas[i]) + "\n");
                }
            }
            escritor.write("SUBTOTAL: " + subtotal + "\n");
            escritor.write("IGV: " + igv + "\n");
            escritor.write("TOTAL: " + totalPagar + "\n");
            escritor.write("Método de Pago: " + metodoPago + "\n");
            if (metodoPago.equals("Efectivo")) {
                escritor.write("Cambio: " + cambio + "\n");
            }
            escritor.write("Gracias por su preferencia.");
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error al exportar la boleta.");
            e.printStackTrace();
        }
    }

    private void libroDeReclamos() {
        Scanner reclamo = new Scanner(System.in);
        String[] lineas = new String[1000];
        int contadorLineas = 0;

        System.out.println("Libro de Reclamos");
        while (true) {
            System.out.println("Opciones:");
            System.out.println("1 - Agregar sugerencias para mejorar la atención");
            System.out.println("2 - Mostrar contenido");
            System.out.println("3 - Guardar y salir");
            System.out.print("Elige una opción: ");

            int opcion = reclamo.nextInt();
            reclamo.nextLine();

            if (opcion == 1) {
                System.out.print("Ingrese su queja o sugerencia para mejorar el servicio: ");
                String linea = reclamo.nextLine();
                if (contadorLineas < lineas.length) {
                    lineas[contadorLineas] = linea;
                    contadorLineas++;
                } else {
                    System.out.println("Se ha alcanzado el límite de líneas.");
                }
            } else if (opcion == 2) {
                System.out.println("Contenido:");
                for (int i = 0; i < contadorLineas; i++) {
                    System.out.println(lineas[i]);
                }
            } else if (opcion == 3) {
                menuPrincipal();
                break;
            } else {
                System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }

        try {
            File archivo = new File("D:\\libro_de_reclamos.txt");
            FileWriter escritor = new FileWriter(archivo);
            escritor.write("---- Libro de Reclamos ---------\n");
            for (int i = 0; i < contadorLineas; i++) {
                escritor.write("Reclamo: " + lineas[i] + "\n");
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error al exportar las quejas.");
            e.printStackTrace();
        }
    }
}
