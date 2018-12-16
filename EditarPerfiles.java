import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class EditarPerfiles implements ActionListener, WindowListener {

  private Label lbldescripcion[]=new Label[5];
  private TextField txtentradas[]=new TextField[5];
  private Button cmdaceptar=new Button("Aceptar");
  private Button cmdcerrar=new Button("Cerrar");
  private TinyFrontEnd tfeventana;
  private int intregistro;
  private Dialog dlgventana;

  public EditarPerfiles(TinyFrontEnd tfeventana,int intregistro) {
    String stretiquetas[]=new String[5];
    StringTokenizer strtpartes;
    this.tfeventana=tfeventana;
    this.intregistro=intregistro;
    stretiquetas[0]=new String("Nombre del perfil:");
    stretiquetas[1]=new String("IP:");
    stretiquetas[2]=new String("Puerto:");
    stretiquetas[3]=new String("Ruta del servidor");
    stretiquetas[4]=new String("Ruta del directorio raiz");
    if (intregistro>=0) {
      strtpartes = new StringTokenizer((String)this.tfeventana.vecperfiles.get(intregistro),"|");    
    }
    else {
      strtpartes = new StringTokenizer("AA|BB|CC|DD|EE","|");    

    }
    dlgventana=new Dialog(new Frame(),"Tiny Web Server Front End",true);
    dlgventana.addWindowListener(this);
    dlgventana.setLayout(new GridLayout(6,2,2,2));
    for(int i=0;i<stretiquetas.length;i++) {
      lbldescripcion[i]=new Label(stretiquetas[i]);
      lbldescripcion[i].setFont(new Font("Serif",Font.BOLD,12));
      lbldescripcion[i].setAlignment(Label.RIGHT);
      dlgventana.add(lbldescripcion[i]);
      txtentradas[i]=new TextField();
      if (intregistro>=0) {
        txtentradas[i].setText(strtpartes.nextToken());
      }
      dlgventana.add(txtentradas[i]);
      
    }
    if (intregistro>=0) {
      txtentradas[0].setEnabled(false);

    }
    cmdaceptar.setFont(new Font("Serif",Font.BOLD,12));
    cmdaceptar.addActionListener(this);
    cmdcerrar.setFont(new Font("Serif",Font.BOLD,12));
    cmdcerrar.addActionListener(this);
    dlgventana.add(cmdaceptar);
    dlgventana.add(cmdcerrar);
    dlgventana.setSize(300,200);
    dlgventana.show();
  }
  
  public void actionPerformed(ActionEvent e){

    if (e.getSource()==cmdaceptar) {
      introducirregistro();
    }
    else {
      cerrarventana();
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
    dlgventana.hide();
    dlgventana.dispose();
  }
  public void windowClosed(WindowEvent e){

  }
  private void introducirregistro() {
    TinyWebServerHandle tftempo;
    for (int i=0;i<txtentradas.length;i++) {
      if (txtentradas[i].getText().trim().equals("")==true) {
        Misc.errormsg(5);
        return;
      }
      else if (txtentradas[i].getText().indexOf("|")!=-1) {
        Misc.errormsg(6);
        return;
      }

    }
    try {
      tftempo=new TinyWebServerHandle(txtentradas[1].getText(),Integer.parseInt(txtentradas[2].getText()),txtentradas[3].getText(),txtentradas[4].getText());        
    }

    catch (NoValidIPException e) {
      Misc.errormsg(1);
      return;
    }
    catch (NoValidPortException e) {
      Misc.errormsg(2);
      return; 
    }
    catch (NoValidTinyPathException e) {
      Misc.errormsg(3);
      return;
    }
    catch (NoValidRootFolderException e) {
      Misc.errormsg(4);
      return;
    }
    catch (Exception e) {
      Misc.errormsg(2);
      return;
    }  

    if (intregistro>=0) {
      tfeventana.vecperfiles.set(this.intregistro,new String(txtentradas[0].getText()+"|"+txtentradas[1].getText()+"|" + Integer.parseInt(txtentradas[2].getText()) + "|" + txtentradas[3].getText() + "|" + txtentradas[4].getText()));      
      tfeventana.salvarperfiles();
      cerrarventana();
    }
    else {
      for (int i=0;i<tfeventana.chperfil.getItemCount();i++) {
        if (tfeventana.chperfil.getItem(i).toUpperCase().equals(txtentradas[0].getText().toUpperCase())==true) {
          Misc.errormsg(7);
          return;
        }
      }
      tfeventana.vecperfiles.add(new String(txtentradas[0].getText()+"|"+txtentradas[1].getText()+"|" + Integer.parseInt(txtentradas[2].getText()) + "|" + txtentradas[3].getText() + "|" + txtentradas[4].getText()));      
      tfeventana.chperfil.add(txtentradas[0].getText());
      tfeventana.salvarperfiles();
      tfeventana.fijarbotones();
      cerrarventana();
    }  
  }

  private void cerrarventana() {
    dlgventana.hide();
    dlgventana.dispose();
  } 
}