
package recursividad;

import recursividad.EmpleadoComun;
import java.util.Calendar;


public class EmpleadoTemporal extends EmpleadoComun{
    private Calendar finContrato;
    
    
    public EmpleadoTemporal(int code, String name){
        super(code, name, 15000);//porque no estara por mucho tiempo se le deja el precio fijo ya que no tendra los mismos beneficios que los otros.
        this.finContrato = Calendar.getInstance(); 
    }
    
    public double pagar(){
        Calendar hoy = Calendar.getInstance();
        if(hoy.before(finContrato))
            return super.pagar();
        return 0;
        
    }
    
    public String toString(){
        return super.toString()+"Fin Contrato= "+finContrato.getTime(); 
    }

    void setFinContrato(Calendar finContrato) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
