/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallermecanico;

import com.taller.dao.AutomovilDao;
import com.taller.modelo.Automovil;
import com.taller.vista.frmRegistroAutomoviles;

/**
 *
 * @author Usuario
 */
public class TallerMecanico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Automovil a = new Automovil();
        AutomovilDao aDao = new AutomovilDao();
        a = aDao.obtenerAutomovilById(2);
        if (a==null){
            System.out.println("Automovil no encontrado");
        } else {
            System.out.println(a.toString());
        }
        
        frmRegistroAutomoviles f = new frmRegistroAutomoviles();
        f.setVisible(true);
    }
    
}
