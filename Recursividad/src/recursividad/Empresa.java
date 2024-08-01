package recursividad;

import java.util.Scanner;
import java.util.Calendar;

public class Empresa {
    
    static Scanner read = new Scanner(System.in);
    static Empleado[] empleados = new Empleado[100]; 
    static int count = 0; 
    
    public static void main(String[] args) {
        int op;
        
        do {
            imprimirMenuPrincipal();
            op = read.nextInt();
            
            switch(op) {
                case 1:
                    hire();
                    break;
                case 2:
                    pay();
                    break;
                case 3:
                    list();
                    break;
                case 4:
                    submenu();
                    break;
                case 5:
                    System.out.println("\nSaliendo del sistema...\n");
                    break;
                default:
                    System.out.println("\nOpción no válida, por favor intente de nuevo.\n");
            }
        } while(op != 5);
    }
    
    private static void imprimirMenuPrincipal() {
        System.out.println("\n***************************");
        System.out.println("*      Menu Principal     *");
        System.out.println("***************************");
        System.out.println("1- Agregar Empleado");
        System.out.println("2- Pagar Empleado");
        System.out.println("3- Lista de Empleados");
        System.out.println("4- Sub Menu Especifico");
        System.out.println("5- Salir");
        System.out.print("Escoja Opcion: ");
    }
    
    private static void imprimirSubMenu() {
        System.out.println("\n***************************");
        System.out.println("*       Sub Menu          *");
        System.out.println("***************************");
        System.out.println("1- Fecha Fin Contrato a Temporales");
        System.out.println("2- Ingresar Venta");
        System.out.println("3- Ingresar Horas de Trabajo");
        System.out.println("4- Regresar al Menu Principal");
        System.out.print("Escoja Opcion: ");
    }
    
//    /**
//     * Recorre la lista de Empleados. Si encuentra un
//     * empleado con ese codigo, se retorna, sino, retorna null
//     * @param cod Codigo del Empleado
//     * @return El obj Empleado si se encuentra, null si no
//     */
    
    private static Empleado search(int cod) {
        for (int index = 0; index < count; index++) {
            if (empleados[index].getCodigo() == cod) {
                return empleados[index];
            }
        }
        return null;
    }

//    /**
//     * Contrata un nuevo empleado
//     * 1- Se pide del teclado el tipo: COMUN, HORA, VENTA o TEMPORAL
//     * 2- Se instancia un objeto segun el tipo y se guarda en el arreglo
//     * 3- pero siempre y cuando el Codigo NO este repetido
//     * 4- LOS DATOS requeridos se ingresan del teclado
//     */
    
    private static void hire() {
        System.out.println("\n***************************");
        System.out.println("*  Contratar Empleado     *");
        System.out.println("***************************");
        System.out.print("Ingrese el tipo de empleado (COMUN, HORA, VENTA, TEMPORAL): ");
        String tipo = read.next().toUpperCase();
        
        System.out.print("Ingrese el codigo: ");
        int codigo = read.nextInt();
        
        if (search(codigo) != null) {
            System.out.println("\nError: Codigo ya existente.\n");
            return;
        }
        
        System.out.print("Ingrese el nombre: ");
        String nombre = read.next();
        
        Empleado nuevoEmpleado = null;
        
        switch(tipo) {
            case "COMUN":
                System.out.print("Ingrese el salario: ");
                double salario = read.nextDouble();
                nuevoEmpleado = new EmpleadoComun(codigo, nombre, salario);
                break;
            case "HORA":
                nuevoEmpleado = new EmpleadoPorHora(codigo, nombre);
                break;
            case "VENTA":
                System.out.print("Ingrese el salario base: ");
                salario = read.nextDouble();
                nuevoEmpleado = new EmpleadoPorVenta(codigo, nombre, salario);
                break;
            case "TEMPORAL":
                nuevoEmpleado = new EmpleadoTemporal(codigo, nombre);
                break;
            default:
                System.out.println("\nError: Tipo de empleado no valido.\n");
                return;
        }
        
        empleados[count++] = nuevoEmpleado;
        System.out.println("\nEmpleado agregado exitosamente.\n");
    }

//    /**
//     * Le paga a un empleado
//     * 1- Pide del teclado el codigo
//     * 2- Buscamos ese empleado
//     * 3- Si existe, mostramos en pantalla su pago
//     */
    
    private static void pay() {
        System.out.println("\n***************************");
        System.out.println("*      Pagar Empleado     *");
        System.out.println("***************************");
        System.out.print("Ingrese el codigo del empleado: ");
        int codigo = read.nextInt();
        
        Empleado emp = search(codigo);
        
        if (emp != null) {
            System.out.println("El pago del empleado es: " + emp.pagar() + "\n");
        } else {
            System.out.println("\nError: Empleado no encontrado.\n");
        }
    }

//    /**
//     * Imprimir la lista de empleados
//     */
    
    private static void list() {
        System.out.println("\n***************************");
        System.out.println("*   Lista de Empleados    *");
        System.out.println("***************************");
        if (count == 0) {
            System.out.println("No hay empleados registrados.\n");
            return;
        }
        
        for (int index = 0; index < count; index++) {
            System.out.println(empleados[index].toString() + "\n");
        }
    }
    
    private static void submenu() {
        int op;
        do {
            imprimirSubMenu();
            op = read.nextInt();
            
            switch(op) {
                case 1:
                    setFin();
                    break;
                case 2:
                    ventas();
                    break;
                case 3:
                    horas();
                    break;
                case 4:
                    System.out.println("\nRegresando al Menu Principal...\n");
                    break;
                default:
                    System.out.println("\nOpción no válida, por favor intente de nuevo.\n");
            }
        } while(op != 4);
    }
    
//    /**
//     * 1- Leer un codigo
//     * 2- Buscar el empleado, que exista y que sea Temporal
//     * 3- Si concuerda, set fecha fin contrato
//     * 4- Leer del teclado los datos
//     */
    
    private static void setFin() {
        System.out.println("\n***************************");
        System.out.println("*  Fecha Fin Contrato     *");
        System.out.println("***************************");
        System.out.print("Ingrese el codigo del empleado temporal: ");
        int codigo = read.nextInt();
        
        Empleado emp = search(codigo);
        
        if (emp != null && emp instanceof EmpleadoTemporal) {
            System.out.print("Ingrese el año de fin de contrato: ");
            int year = read.nextInt();
            System.out.print("Ingrese el mes de fin de contrato: ");
            int month = read.nextInt();
            System.out.print("Ingrese el dia de fin de contrato: ");
            int day = read.nextInt();
            
            Calendar finContrato = Calendar.getInstance();
            finContrato.set(year, month, day);
            ((EmpleadoTemporal) emp).setFinContrato(finContrato);
            
            System.out.println("\nFecha de fin de contrato actualizada.\n");
        } else {
            System.out.println("\nError: Empleado no encontrado o no es temporal.\n");
        }
    }

//    /**
//     * 1- Leer un codigo
//     * 2- Buscar empleado, que exista y que sea PorVentas
//     * 3- Si concuerda, agregar una venta
//     * 4- Leer del teclado los datos
//     */
    
    private static void ventas() {
        System.out.println("\n***************************");
        System.out.println("*     Ingresar Venta      *");
        System.out.println("***************************");
        System.out.print("Ingrese el codigo del empleado por ventas: ");
        int codigo = read.nextInt();
        
        Empleado emp = search(codigo);
        
        if (emp != null && emp instanceof EmpleadoPorVenta) {
            System.out.print("Ingrese el monto de la venta: ");
            double monto = read.nextDouble();
            ((EmpleadoPorVenta) emp).agregarVentas(monto);
            
            System.out.println("\nVenta agregada exitosamente.\n");
        } else {
            System.out.println("\nError: Empleado no encontrado o no es por ventas.\n");
        }
    }

//    /**
//     * 1- Leer un codigo
//     * 2- Buscar empleado, que exista y que sea PorHoras
//     * 3- Si concuerda, agregar las horas trabajadas
//     * 4- Leer del teclado los datos
//     */
    
    private static void horas() {
        System.out.println("\n***************************");
        System.out.println("*  Ingresar Horas Trabajo *");
        System.out.println("***************************");
        System.out.print("Ingrese el codigo del empleado por horas: ");
        int codigo = read.nextInt();
        
        Empleado emp = search(codigo);
        
        if (emp != null && emp instanceof EmpleadoPorHora) {
            System.out.print("Ingrese la cantidad de horas trabajadas: ");
            int horas = read.nextInt();
            ((EmpleadoPorHora) emp).setHorasT(horas);
            
            System.out.println("\nHoras agregadas exitosamente.\n");
        } else {
            System.out.println("\nEmpleado no encontrado o no es por horas.\n");
        }
    }
}
