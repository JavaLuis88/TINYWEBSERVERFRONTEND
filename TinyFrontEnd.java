import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
public class TinyFrontEnd implements ActionListener, WindowListener{


  private Label lbldescripcion=new Label("Perfil:");
  Choice chperfil=new Choice();
  private Button cmdstart=new Button("Iniciar");
  private Button cmdstop=new Button("Parar");
  private Button cmdnuevo=new Button("Nuevo Perfil");
  private Button cmdeditar=new Button("Editar Perfil");
  private Button cmdborrar=new Button("Borrar Perfil");
  private Button cmdsalir=new Button("Salir");
  private Dialog dlgventana;
  private TinyWebServerHandle twshservidor=new TinyWebServerHandle();
  Vector vecperfiles = new Vector();
  
  public  TinyFrontEnd() {

    BufferedReader brlineas;
    String strlineas;
    StringTokenizer strtpartes;
    lbldescripcion.setAlignment(Label.RIGHT);
    lbldescripcion.setFont(new Font("Serif",Font.BOLD,12));
    dlgventana=new Dialog(new Frame(),"Tiny Web Server Front End");
    dlgventana.addWindowListener(this);
    dlgventana.setLayout(new GridLayout(4,2,2,2));
    cmdstart.addActionListener(this);
    cmdstop.addActionListener(this);
    cmdnuevo.addActionListener(this);
    cmdeditar.addActionListener(this);
    cmdborrar.addActionListener(this);
    cmdsalir.addActionListener(this);
    cmdstart.setFont(new Font("Serif",Font.BOLD,12));
    cmdstop.setFont(new Font("Serif",Font.BOLD,12));
    cmdnuevo.setFont(new Font("Serif",Font.BOLD,12));
    cmdeditar.setFont(new Font("Serif",Font.BOLD,12));
    cmdborrar.setFont(new Font("Serif",Font.BOLD,12));
    cmdsalir.setFont(new Font("Serif",Font.BOLD,12));
    dlgventana.setSize(300,200);
    dlgventana.add(lbldescripcion);
    dlgventana.add(chperfil);
    dlgventana.add(cmdstart);
    dlgventana.add(cmdstop);
    dlgventana.add(cmdnuevo);
    dlgventana.add(cmdeditar);
    dlgventana.add(cmdborrar);
    dlgventana.add(cmdsalir);
    
    try {
      brlineas=new BufferedReader(new FileReader(new File("perfiles.txt")));
      while(true) {
        strlineas=brlineas.readLine();
        if (strlineas==null) {
          break;
        }
        if (strlineas.trim().equals("")==false) {
          vecperfiles.add(strlineas);
          strtpartes=new StringTokenizer(strlineas,"|");
          chperfil.add(strtpartes.nextToken());
        } 
      }

    }
    catch (Exception e) {


    }
    fijarbotones();

    dlgventana.show(); 
        
    

  } 

  void fijarbotones() {
    cmdstop.setEnabled(false);
    cmdnuevo.setEnabled(true);
    cmdsalir.setEnabled(true);


    if (vecperfiles.size()>=1) {
      cmdstart.setEnabled(true);
      cmdeditar.setEnabled(true);
      cmdborrar.setEnabled(true);

    }
    else {
      cmdstart.setEnabled(false);
      cmdeditar.setEnabled(false);
      cmdborrar.setEnabled(false); 
    }
  }
  
  private void pararservidor() {
    try {
      twshservidor.Stop();
      fijarbotones();  
    }
    catch (RunningServerException e) {
      Misc.errormsg(0);
    }
  }
  private void arrancarservidor() { 
    try {
      String strtempo="";   
      StringTokenizer strtpartes=new StringTokenizer((String)vecperfiles.get(chperfil.getSelectedIndex()) ,"|"); 
      strtempo=strtpartes.nextToken();
      twshservidor.setIP(strtpartes.nextToken());
      twshservidor.setPort(Integer.parseInt(strtpartes.nextToken()));
      twshservidor.setTinyFolder(strtpartes.nextToken());
      twshservidor.setRootFolder(strtpartes.nextToken());
      twshservidor.Start();
      cmdstop.setEnabled(true);
      cmdnuevo.setEnabled(false);
      cmdsalir.setEnabled(true);
      cmdstart.setEnabled(false);
      cmdeditar.setEnabled(false);
      cmdborrar.setEnabled(false);
    }
    catch (RunningServerException e) {
      Misc.errormsg(0);
    }
    catch (NoValidIPException e) {
      Misc.errormsg(1);
    }
    catch (NoValidPortException e) {
      Misc.errormsg(2);
    }
    catch (NoValidTinyPathException e) {
      Misc.errormsg(3);
    }
    catch (NoValidRootFolderException e) {
      Misc.errormsg(4);
    }
    catch (Exception e) {
      Misc.errormsg(2);
    }
  }
  private void cerrarfrontend() {
    try {
      twshservidor.Stop();
    }
    catch(RunningServerException i){
    } 
    dlgventana.hide();
    dlgventana.dispose();
    System.exit(0);
  }

  private void borrarperfil() {
    vecperfiles.remove(chperfil.getSelectedIndex());    
    chperfil.remove(chperfil.getSelectedIndex());    
    salvarperfiles();
    fijarbotones();
  }
  void salvarperfiles() {
    try {
      File filperfiles=new File("perfiles.txt");
      PrintStream prsperfiles;

      if (filperfiles.exists()==true && filperfiles.isFile()==true) {
        filperfiles.delete();
      } 
      prsperfiles = new PrintStream(new FileOutputStream(filperfiles));
      for (int i=0;i<vecperfiles.size();i++) {
        prsperfiles.println((String)vecperfiles.get(i));
      }
      prsperfiles.flush();
      prsperfiles.close();
    }
    catch (Exception e) {

    }
  }


  private void nuevoperfil() {
    EditarPerfiles e=new EditarPerfiles(this,-1);
  }

  private void editarperfil() {
    EditarPerfiles e = new EditarPerfiles(this,chperfil.getSelectedIndex());
  }


  public void actionPerformed(ActionEvent e){

    if (e.getSource()==cmdstop) {
      pararservidor();
    }
    else if (e.getSource()==cmdstart) {
      arrancarservidor(); 
    }
    else if (e.getSource()==cmdsalir) {
      cerrarfrontend();
    }
    else if (e.getSource()==cmdborrar) {
      borrarperfil();
    }
    else if (e.getSource()==cmdnuevo) {
      nuevoperfil();
    }
    else if (e.getSource()==cmdeditar) {
      editarperfil();
    }
  }
  public void windowOpened(WindowEvent e){
    	
  }
  public void windowActivated(WindowEvent e){

  }
  public void windowDeactivated(WindowEvent e){

  }
  public void windowIconified(WindowEvent e){

  }
  public void windowDeiconified(WindowEvent e){
   
  }
  public void windowClosing(WindowEvent e){
    try {
      twshservidor.Stop();
    }
    catch(RunningServerException i){
    } 
    dlgventana.hide();
    dlgventana.dispose();
    System.exit(0);
  }
  public void windowClosed(WindowEvent e){

  }



  public static void main(String args[]) {
    new TinyFrontEnd();
  }

}