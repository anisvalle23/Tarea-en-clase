
package recursividad;

import recursividad.EmpleadoComun;
import java.util.Calendar;


public class EmpleadoPorVenta extends EmpleadoComun  {
    private double ventas[], tasa;
    public EmpleadoPorVenta(int code, String name, double salary){
         super(code, name, salary);
         ventas = new double[12];
         tasa=0.05;
    }
    
   private int mesActual() {
    Calendar fecha = Calendar.getInstance();
    int mes = fecha.get(Calendar.MONTH); 
    return mes;
}

public void agregarVentas(double monto){
        ventas[mesActual()] += monto; 
}
public double comision(){
    return ventas[mesActual()] * tasa;
      
    
}
      public double pagar() {//
        return super.pagar() + comision(); //esto es polimorfismo 
    }
      
       public String toString() { //es para la salida de la pantalla
        return super.toString() + "Comisión=$" + comision();
}

}