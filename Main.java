package org.example;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        boolean connection2 = true;
        Connection conn1 = null;
        try {
            conn1 = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5434/test-db",
                    "utu",
                    "12345678"
            );
        } catch (Exception j) {
            connection2 = false;
            j.printStackTrace();
        }

        if(connection2){
            System.out.println("[✔\uFE0F] Conexion establecida correctamente!");
        }else{
            System.out.println("[❌] Algo salio mal.");
            System.out.println("Revise si tiene el: " );
            System.out.println("➜ La carpeta Docker Activada.");
            System.out.println("➜ Si tiene el docker abierto.");
            System.out.println("➜ Si el puerto es el correcto.");
        }
        // Welcome / Bienvenida
        System.out.println("Bienvenido a la biblioteca! ¿Qué categoría desea?");
        for (Genero genero : Genero.values()) {
            System.out.println("⮞ " + genero);
        }

        String categoria[] = new String[1];
        String CI[] = new String[1];

        // Ventana
        JFrame ventana = new JFrame("Conectividad entre Java y Docker");
        ventana.setSize(400, 300); // ancho x alto en píxeles
        ventana.setLayout(null);  // Desactiva el layout manager
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        // Crear una etiqueta(Label)
        JLabel etiqueta = new JLabel("Bienvenido a la biblioteca! ¿Qué categoría desea?");
        etiqueta.setBounds(50, 30, 300, 25);
        ventana.add(etiqueta);

        // Crear un CampoDeTexto(TextField)
        JTextField area = new JTextField();
        area.setBounds(100, 60, 180, 25);
        ventana.add(area);

        JLabel datos = new JLabel("📝 RELLENE LOS DATOS (Cédula)");
        datos.setBounds(50, 90, 300, 25);
        ventana.add(datos);

        // Crear un CampoDeTexto(TextField)
        JTextField area2 = new JTextField();
        area2.setBounds(100, 120, 180, 25);
        ventana.add(area2);



        // Crear el boton enviar
        JButton boton = new JButton("Enviar");
        boton.setBounds(130, 200, 100, 30);
        ventana.add(boton);

        ventana.setVisible(true);

        // La accion del booton
        boton.addActionListener(e ->{
            categoria[0] = area.getText();
            CI[0] = area2.getText();

            JOptionPane.showMessageDialog(ventana, "[✔\uFE0F] Enviado con exito!");
            boolean connection = true;
            Connection conn = null;
            // test

            try {
                conn = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5434/test-db",
                        "utu",
                        "12345678"
                );
            } catch (Exception j) {
                connection = false;
                j.printStackTrace();
            }

            // Scanner
            Scanner sc = new Scanner(System.in);

            // Variables
            Genero cat = Genero.FICCION;
            boolean error =  false;

            // Check the option / Si está la opción
            String result = categoria[0].trim().toUpperCase();;
            try {
                cat = Genero.valueOf(result);
            } catch (IllegalArgumentException d) {
                System.out.println("Error -> Categoría no encontrada!");
                error = true;
            }
            Persona persona = new Persona(true, "0");
            datosPersona(persona, sc, CI);


            // Creo el ArrayList para los autores
            ArrayList<Autor> autores = new ArrayList<>();

            // Los establezco
            setAutores(autores);

            // Creo el ArrayList para los libros
            ArrayList<Libro> array = new ArrayList<>();

            // Creo el ArrayList para los Ejemplarse
            ArrayList<Ejemplar> ejemplarse = new ArrayList<>();

            // Los establezco
            setLibros(array, autores, ejemplarse);

            // Ejemplares
            setEjemplarse(ejemplarse, array);

            // comparo por categoria para encontrar lo que me pide el cliente
            if (!error) {
                for (Libro each : array) {
                    if (each.getGenero() == cat) {
                        System.out.println("[✔\uFE0F] Categoría encontrada! ");
                        each.showInfo();
                        System.out.println("=================");
                    }
                }

            }

            // Crear ArrayList de Prestamos
            ArrayList prestamos = new ArrayList();



            ventana.setVisible(false);
            boolean input = false;

            do {
                input = pedirPrestamo(array, sc, persona, ejemplarse, prestamos);
            }while (!input);



            // Bibloteca en general
            Bibloteca bibloteca = new Bibloteca(array, prestamos);

            UserInfo(persona, bibloteca, ejemplarse, prestamos);



            // Base de Datos
            if(connection){
                // se insteran los datos de las tablas que no tienen FK para que cuando se inserte los datos no tire ERROR
                // por que si no la tabla intetaria buscar si el dato esta en la tabla PK
                // y daria ERROR

                // Tablas independientes
                saveAuthorsInBD(autores, conn);
                saveLibroInBD(array, conn);
                savePersonaInBD(persona, conn);


                // Tablas dependientes
                saveEjemplarInBD(ejemplarse, conn);


            }
            ventana.dispose(); // cierra la ventana
            System.exit(0);    // cierra la JVM completamente
        });

    }

     // Local
    public static void datosPersona(Persona persona, Scanner sc,String[] ci){
        System.out.println("\n" +
                "\n" +
                "\uD83D\uDCDD RELLENE LOS DATOS");
        System.out.println("=================");
        System.out.print("Cédula: ");
        persona.setCedula(ci[0]);
        System.out.println(ci[0]);
        System.out.println("=================");
    }

    public static void setEjemplarse(ArrayList<Ejemplar> ejemplarse, ArrayList<Libro> libros){
        ejemplarse.add(new Ejemplar(1, libros.get(0), Estado.PRESTADO));
        ejemplarse.add(new Ejemplar(2, libros.get(1), Estado.EN_BIBLIOTECA));
        ejemplarse.add(new Ejemplar(3, libros.get(2), Estado.RETRASADO));
        ejemplarse.add(new Ejemplar(4, libros.get(3), Estado.PRESTADO));
        ejemplarse.add(new Ejemplar(5, libros.get(4), Estado.EN_BIBLIOTECA));
        ejemplarse.add(new Ejemplar(6, libros.get(5), Estado.RETRASADO));
        ejemplarse.add(new Ejemplar(7, libros.get(6), Estado.EN_BIBLIOTECA));
        ejemplarse.add(new Ejemplar(8, libros.get(7), Estado.PRESTADO));
        ejemplarse.add(new Ejemplar(9, libros.get(8), Estado.RETRASADO));
        ejemplarse.add(new Ejemplar(10, libros.get(9), Estado.EN_BIBLIOTECA));
    }

    public static void setAutores(ArrayList<Autor> autores){
        autores.add(new Autor(1, "Gabriel García Márquez", "Colombiano", new DTFecha(6, 3, 1927)));
        autores.add(new Autor(2, "Mario Vargas Llosa", "Peruano", new DTFecha(28, 3, 1936)));
        autores.add(new Autor(3, "Isabel Allende", "Chilena", new DTFecha(2, 8, 1942)));
        autores.add(new Autor(4, "Jorge Luis Borges", "Argentino", new DTFecha(24, 8, 1899)));
        autores.add(new Autor(5, "Julio Cortázar", "Argentino", new DTFecha(26, 8, 1914)));
        autores.add(new Autor(6, "Laura Esquivel", "Mexicana", new DTFecha(30, 9, 1950)));
        autores.add(new Autor(7, "Carlos Ruiz Zafón", "Español", new DTFecha(25, 9, 1964)));
        autores.add(new Autor(8, "Paulo Coelho", "Brasileño", new DTFecha(24, 8, 1947)));
        autores.add(new Autor(9, "Arturo Pérez-Reverte", "Español", new DTFecha(25, 11, 1951)));
        autores.add(new Autor(10, "Juan Rulfo", "Mexicano", new DTFecha(16, 5, 1917)));
    }

    public static void setLibros(ArrayList<Libro> libros, ArrayList<Autor> autores, ArrayList<Ejemplar> ejemplarse){
        libros.add(new Libro("Cien años de soledad", Genero.FICCION, "Sudamericana", new DTFecha(5, 6, 1967), "9780060883287", autores, ejemplarse));
        libros.add(new Libro("La ciudad y los perros", Genero.DRAMA, "Seix Barral", new DTFecha(10, 6, 1963), "9788432205602", autores, ejemplarse));
        libros.add(new Libro("La casa de los espíritus", Genero.DRAMA, "Plaza & Janés", new DTFecha(12, 1, 1982), "9788401337208", autores, ejemplarse));
        libros.add(new Libro("Ficciones", Genero.FICCION, "Sur", new DTFecha(1, 1, 1944), "9788420633127", autores, ejemplarse));
        libros.add(new Libro("Rayuela", Genero.FICCION, "Sudamericana", new DTFecha(28, 6, 1963), "9788437604942",autores, ejemplarse));
        libros.add(new Libro("Como agua para chocolate", Genero.ROMANCE, "Planeta", new DTFecha(1, 9, 1989), "9780385420174", autores, ejemplarse));
        libros.add(new Libro("La sombra del viento", Genero.MISTERIO, "Planeta", new DTFecha(17, 4, 2001), "9788408172178", autores, ejemplarse));
        libros.add(new Libro("El alquimista", Genero.FICCION, "Rocco", new DTFecha(1, 1, 1988), "9780061122415", autores, ejemplarse));
        libros.add(new Libro("La tabla de Flandes", Genero.MISTERIO, "Alfaguara", new DTFecha(1, 1, 1990), "9788420467289", autores, ejemplarse));
        libros.add(new Libro("Pedro Páramo", Genero.DRAMA, "Fondo de Cultura Económica", new DTFecha(1, 1, 1955), "9789681604823", autores, ejemplarse));
    }

    public static boolean pedirPrestamo(ArrayList<Libro> array, Scanner sc, Persona cliente, ArrayList<Ejemplar> ejemplarse, ArrayList<Prestamo> prestamos) {
        System.out.println("¿Qué libro quieres elegir? *Elige con el código*");

        // Mostramos los libros con índices como códigos
        for (int i = 0; i < array.size(); i++) {
            Libro libro = array.get(i);
            Ejemplar ejemplar = ejemplarse.get(i);

            System.out.printf("%d) %-40s Estado: %s%n", (i + 1), libro.getTitulo(), ejemplar.getEstado());
        }


        // Opción para salir
        System.out.println((array.size() + 1) + ") Salir");

        int codigo = sc.nextInt();

        // Salir
        if (codigo == array.size() + 1) {
            return true;
        }

        // Validación
        if (codigo < 1 || codigo > array.size()) {
            System.out.println("Libro no encontrado! Intente de nuevo!");
            return false;
        }

        // Obtener datos de Libro y Ejemplar
        Ejemplar ejemplar = ejemplarse.get(codigo - 1);
        Libro libro = array.get(codigo - 1);



        if (ejemplar.getEstado().name().equals("EN_BIBLIOTECA")) {
            LocalDate hoy = LocalDate.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fecha_emison = hoy.format(formato);
            Prestamo prestamo = new Prestamo("22/8/25", fecha_emison, false, ejemplar.getIdEjemplar());

            System.out.println("Libro reservado con éxito!");
            System.out.println("======================");
            System.out.println("Ticket:");
            System.out.println(" | CI: " + cliente.getCedula());
            System.out.println(" | Fecha Emision: " + prestamo.getFecha_emision());
            System.out.println(" | Fecha Entrega: " + prestamo.getFecha_entrega());
            libro.showInfo();
            System.out.println("======================");
            prestamos.add(prestamo);
            ejemplar.setEstado(Estado.PRESTADO);
        } else {
            System.out.println("Libro ya reservado!");
        }
        return false;
    }

    public static void UserInfo(Persona persona, Bibloteca bibloteca, ArrayList<Ejemplar> ejemaplares, ArrayList<Prestamo> prestamos){
        System.out.println("Info del Usuario: ");
        persona.showInfo();
        if(!prestamos.isEmpty()){
            System.out.println("Prestamos: ");
            int i = 0;
            for (Prestamo prestamo: bibloteca.getPrestamo()){
                i++;
                System.out.println("          ");
                System.out.println("Prestamo " + i);
                prestamo.showInfo();
                for(Ejemplar ejemplar: ejemaplares){
                    if(prestamo.getId_libro() == ejemplar.getIdEjemplar()){
                        ejemplar.getLibro().showInfo();
                    }
                }

            }
        }else{
            System.out.println("[❌] No selecionaste ningun libro como para tener prestamos. ");
        }

    }


    // Base de Datos
    public static void saveAuthorsInBD(ArrayList<Autor> autores, Connection conn) {
        String sql = "INSERT INTO Autor (nombre, nacionalidad, fecha_nac) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (Autor autor : autores) {
                stmt.setString(1, autor.getNombre());
                stmt.setString(2, autor.getNacionalidad());

                // Si DTFecha es tu clase personalizada, convertimos a formato "dd/MM/yyyy"
                String fechaNac = String.format("%02d/%02d/%04d",
                        autor.getFecha_nec().getDia(),
                        autor.getFecha_nec().getMes(),
                        autor.getFecha_nec().getAno()
                );
                stmt.setString(3, fechaNac);

                stmt.addBatch(); // acumulamos en batch
            }
            stmt.executeBatch(); // ejecutamos
            System.out.println("[\uD83D\uDCDA] Autores guardados correctamente en la tabla Autor.");
        } catch (SQLException e) {
            System.out.println("[⚠\uFE0F] Error en guardar datos en la tabla Autor");
            ErrorMessageBD();
            e.printStackTrace();
        }
    }
    public static void saveLibroInBD(ArrayList<Libro> libros, Connection conn){
        String sql = "INSERT INTO Libro (isbn, titulo, genero, editorial, fecha_pub) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            for(Libro libro: libros){
                stmt.setString(1, libro.getISBN());
                stmt.setString(2, libro.getTitulo());
                stmt.setString(3, libro.getGenero().name());
                stmt.setString(4, libro.getEditorial());
                String fechapub = String.format("%02d/%02d/%04d",
                        libro.getFechaPub().getDia(),
                        libro.getFechaPub().getMes(),
                        libro.getFechaPub().getAno()
                );
                stmt.setString(5, fechapub);

                stmt.addBatch();
            }
            stmt.executeBatch();
            System.out.println("[\uD83D\uDCDA] Libros guardados correctamente en la tabla Libro. ");
        } catch (SQLException e) {
            System.out.println("[⚠\uFE0F] Error en guardar datos en la tabla Libro");
            ErrorMessageBD();
            e.printStackTrace();
        }

    }
    public static void savePersonaInBD(Persona persona, Connection conn){
        String sql = "INSERT INTO Persona(CI) VALUES(?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, persona.getCedula());
            stmt.addBatch();
            stmt.executeBatch();

            System.out.println("[\uD83D\uDCDA] Persona ha sido agregada correctamente a la tabla Persona. ");
        }catch(SQLException e){
            System.out.println("[⚠\uFE0F] Error en guardar datos en la tabla Persona");
            ErrorMessageBD();
            e.printStackTrace();
        }
    }

    public static void saveEjemplarInBD(ArrayList<Ejemplar> ejemplares, Connection conn){
        String sql = "INSERT INTO Ejemplar(idEjemplar, isbn, estado) VALUES (?, ? ,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            for(Ejemplar ejemplar : ejemplares){
                stmt.setInt(1, ejemplar.getIdEjemplar());
                stmt.setString(2, ejemplar.getLibro().getISBN());
                stmt.setString(3, ejemplar.getEstado().name());

                stmt.addBatch();
                stmt.executeBatch();

            }
            System.out.println("[\uD83D\uDCDA] Ejemplares ha sido aregado correctamente a la tabla Ejemplar. ");
        } catch (SQLException e) {
            System.out.println("[⚠\uFE0F] Error en guardar datos en la tabla Ejemplar. ");
            ErrorMessageBD();
            e.printStackTrace();
        }
    }

    public static void ErrorMessageBD(){
        System.out.println("Posibles causas: ");
        System.out.println("➜ La tabla ya esta creada por lo tanto colisionan los PK'S. ");
        System.out.println("➜ Dominios o tipos de datos que no conicidan. ");
        System.out.println("➜ O mala estructuración en la importacion de datos a la Base de Datos. ");
    }




}