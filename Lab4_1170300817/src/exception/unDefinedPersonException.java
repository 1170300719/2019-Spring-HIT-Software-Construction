

package exception;

public class unDefinedPersonException extends Exception {

	private static final long serialVersionUID = 1L;

	/**���췽��
	 * @param info ������Ϣ
	 */
	public unDefinedPersonException(String info) {
    super(info);
  }

}
