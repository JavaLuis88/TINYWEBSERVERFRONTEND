public class NoValidRootFolderException extends Exception {

  public NoValidRootFolderException() {


    super("La ruta del directorio raiz no es valida");
  
  }
  public NoValidRootFolderException(String strmensaje) {


    super(strmensaje);
  
  }
  

}