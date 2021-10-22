
public class InvalidInputException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3613289305328322102L;

	public InvalidInputException()
	{
		super("Invalid response. Do you consent to have your decisions saved to a file? (yes/no)");
	}

}
