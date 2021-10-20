class CustomException extends Exception {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String message;
   CustomException(String str) {
      message = str;
   }
   public String toString() {
      return ("WARNING: " + message);
   }
}