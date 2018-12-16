public class NoValidIPException extends Exception {

  public NoValidIPException() {


    super("La IP no es valida");
  
  }
  public NoValidIPException(String strmensaje) {


    super(strmensaje);
  
  }
  

}