
public class InvalidCharacteristicException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1414012765792937145L;

	public InvalidCharacteristicException()
	{
		super("WARNING: invalid characteristic in config file in line");
	}
}
