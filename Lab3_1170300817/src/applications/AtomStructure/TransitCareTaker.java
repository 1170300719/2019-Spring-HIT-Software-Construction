package applications.AtomStructure;

import java.util.Stack;

/**
 * @author Administrator ������¼����
 */
public class TransitCareTaker {
	private Stack<Memento> mementos = new Stack<>();

	/**
	 * ���췽��
	 * 
	 * @param e
	 */
	public void addMemento(Memento e) {
		mementos.push(e);
	}

	/**
	 * �ָ�����ջ��һ��mementos
	 * 
	 * @return
	 */
	public Memento getMemento() {
		if (!mementos.empty()) {
			return mementos.pop();
		} else {
			return null;
		}
	}

}
