public class RunningServerException extends Exception {

  public RunningServerException() {


    super("Error al arracar/parar el Tiny Web Server");
  
  }
  public RunningServerException(String strmensaje) {


    super(strmensaje);
  
  }
  

}