package calculadoras;

@SuppressWarnings("serial")
public class DivisionByZeroException extends Exception {
	public DivisionByZeroException() {
		super("Imposs�vel divis�o por zero!!!\n\n");
	}
}
