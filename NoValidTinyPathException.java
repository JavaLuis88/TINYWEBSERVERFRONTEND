public class NoValidTinyPathException extends Exception {

  public NoValidTinyPathException() {


    super("El directorio del tiny Web Server no es valida");
  
  }
  public NoValidTinyPathException(String strmensaje) {


    super(strmensaje);
  
  }
  

}