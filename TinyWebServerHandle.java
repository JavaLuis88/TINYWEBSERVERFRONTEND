import java.util.StringTokenizer;
import java.io.File;
public class TinyWebServerHandle {



  private String strip="127.0.0.1";
  private int intport=80;
  private String strtinywebserverdirectory="";
  private String strrootfolder="";
  private Process prtiny=null;

  public TinyWebServerHandle() {

    this.strip="127.0.0.1";
    this.intport=80;
    this.strtinywebserverdirectory="";
    this.strrootfolder="";

  }

  public TinyWebServerHandle(String strip,int intport) throws NoValidIPException,NoValidPortException {

    if (esipvalida(strip)==false) {
      throw new NoValidIPException();
    } 
    else if (intport<1) {
      throw new NoValidPortException();
    }  
    this.strip=strip;
    this.intport=intport;
    this.strtinywebserverdirectory="";
    this.strrootfolder="";
  }


  public TinyWebServerHandle(String strip,int intport,String strtinypath,String strrootfolder) throws NoValidIPException,NoValidPortException,NoValidTinyPathException,NoValidRootFolderException{
  
    if (esipvalida(strip)==false) {
      throw new NoValidIPException();
    } 
    else if (intport<1) {
      throw new NoValidPortException();
    } 
    else if (esvalidotinydirectorio(strtinypath)==false) {
      throw new NoValidTinyPathException();
    } 
    else if (esvalidodirectorioraiz(strrootfolder)==false) {
      throw new NoValidRootFolderException();
    } 
    this.strip=strip;
    this.intport=intport;
    this.strtinywebserverdirectory=strtinypath;
    this.strrootfolder=strrootfolder;
  }

 
  public void setIP(String strip) throws NoValidIPException {
    if (esipvalida(strip)==false) {
      throw new NoValidIPException();
    } 
    this.strip=strip;
  }
  public String getIP() {

    return this.strip;
  }
 
  public void setPort(int intport) throws NoValidPortException {
    if (intport<1) {
      throw new NoValidPortException();
    } 
    this.intport=intport;
  }
  public int getPort() {
    return this.intport;
  }
  public void setTinyFolder(String strtinypath) throws NoValidTinyPathException {
    if (esvalidotinydirectorio(strtinypath)==false) {
      throw new NoValidTinyPathException();
    } 
    this.strtinywebserverdirectory=strtinypath;
  }
  public String getTinyFolder() {
    return this.strtinywebserverdirectory;
  }
  public void setRootFolder(String strrootfolder) throws NoValidRootFolderException {

    if (esvalidodirectorioraiz(strrootfolder)==false) {
      throw new NoValidRootFolderException();
    } 
    this.strrootfolder=strrootfolder;
  }
  
  public String getRootFolder() {

    return this.strrootfolder;
  }
  
  public void Start() throws NoValidIPException,NoValidPortException,NoValidTinyPathException,NoValidRootFolderException,RunningServerException {
    String strcomando;
    this.Stop();
    if (esipvalida(this.strip)==false) {
      throw new NoValidIPException();
    } 
    else if (this.intport<1) {
      throw new NoValidPortException();
    } 
    else if (esvalidotinydirectorio(this.strtinywebserverdirectory)==false) {
      throw new NoValidTinyPathException();
    } 
    else if (esvalidodirectorioraiz(this.strrootfolder)==false) {
      throw new NoValidRootFolderException();
    }
 
    
    strcomando="" + strtinywebserverdirectory;
    
    if (strcomando.charAt(strcomando.length()-1)!='\\') {
      
      strcomando=strcomando+"\\";
  
    }
 
    strcomando=strcomando+"TINY.EXE " +this.strrootfolder + " " +this.intport + " " +this.strip;
    try {
      prtiny=Runtime.getRuntime().exec(strcomando);
    }
    catch (Exception e) {
      throw new RunningServerException();
    }

  }
  public void Stop() throws RunningServerException {
    try {
      if (prtiny!=null) {
        prtiny.destroy(); 
        prtiny=null;
      }
    }
    catch (Exception e) {

      throw new RunningServerException();
   
    }
  }    

  private boolean esipvalida(String strip) {
    try {
      StringTokenizer strtip = new StringTokenizer(strip,".");
      int intip=0;

      if (strtip.countTokens()!=4) {
        return false;
      }
      while (strtip.hasMoreTokens()) {
        intip=Integer.parseInt(strtip.nextToken());
        if (intip<0 || intip>255) {
          return false;
        }
      }
    }
    catch(Exception e) {
      return false;
    }
    return true;
  }

  private boolean esvalidotinydirectorio(String strdirectorio) {
    String strruta="";
    File filarchivo;

    strruta="" + strdirectorio;
    
    if (strruta.charAt(strruta.length()-1)!='\\') {
      
      strruta=strruta+"\\";
  
    }
    strruta=strruta + "tiny.exe";
    filarchivo=new File(strruta);

    if (filarchivo.exists()==false || filarchivo.isFile()==false) {
      return false;
    }
    else {
      return true;
    }

  }
  private boolean esvalidodirectorioraiz(String strdirectorio) {
    String strruta="";
    File filarchivo;

    strruta="" + strdirectorio;
    
    if (strruta.charAt(strruta.length()-1)!='\\') {
      
      strruta=strruta+"\\";
  
    }
    strruta=strruta + "index.html";
    filarchivo=new File(strruta);

    if (filarchivo.exists()==false || filarchivo.isFile()==false) {
      return false;
    }
    else {
      return true;
    }


  }

 
}
