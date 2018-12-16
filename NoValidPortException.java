public class NoValidPortException extends Exception {

  public NoValidPortException() {


    super("El puerto no es valido");
  
  }
  public NoValidPortException(String strmensaje) {


    super(strmensaje);
  
  }
  

}