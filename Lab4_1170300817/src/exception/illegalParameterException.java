package exception;

public class illegalParameterException extends Exception{
	private static final long serialVersionUID = 1L;

	/**
	 * ���췽��
	 * 
	 * @param info ������Ϣ
	 */
	public illegalParameterException(String info) {
		super(info);
	}
}
