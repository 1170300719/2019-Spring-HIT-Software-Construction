package exception;

public class illegalTextGrammarException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * ���췽��
	 * 
	 * @param info ������Ϣ
	 */
	public illegalTextGrammarException(String info) {
		super(info);
	}

}
