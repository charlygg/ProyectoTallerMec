/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallermecanico;

import com.taller.dao.AutomovilDao;
import com.taller.modelo.Automovil;
import com.taller.vista.frmRegistroAutomoviles;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Usuario
 */
public class TallerMecanico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        /*Look and Feel Nimbus de Java*/   
         try {
          for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
             UIManager.setLookAndFeel(info.getClassName());
                        System.out.println("CHOSEN THIS");
              break;
         }
         else{
             UIManager.setLookAndFeel  ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
          }
        }
        } catch (Exception e) {
        // If Nimbus is not available, you can set to another look and feel.
        //Cant get it to compile or work.

}
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
