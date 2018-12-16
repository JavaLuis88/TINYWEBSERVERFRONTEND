import java.awt.*;
import javax.swing.*;

public class Misc {



  public static void errormsg(int intnumero) {
    String[] strerrores=new String[8];
    strerrores[0]="Error al iniciar/terminar el servidor";
    strerrores[1]="La IP Especificada en este perfil no es valida";
    strerrores[2]="El puerto especificado en este perfil no es valido";
    strerrores[3]="La ruta del servidor web no es valida";
    strerrores[4]="La ruta del directorio raiz no es valida o no tiene un archivo index.html";
    strerrores[5]="No puedes dejar los campos en blanco o solo con espacios";
    strerrores[6]="No puedes introducir el caracter | en los campos";
    strerrores[7]="Ya existe un perfil con este nombre";

    JOptionPane.showMessageDialog(new Frame(),strerrores[intnumero],"Error",JOptionPane.WARNING_MESSAGE); 

  }
}