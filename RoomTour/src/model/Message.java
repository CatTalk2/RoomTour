package model;

/**
 * Message�ࣺ��Ҫ���ڸ���TextArea�е����֣��ɵ����򵥸�����
 *
 * @author  Song Ya rong;
 * @version 1.0
 */

public class Message {
	public static final String WEILCOME_MESSAGE = "Welcome to Room Tour";
	public static final String FINISH_MESSAGE = "Congratulation! "
			+ "You find all! \n ";
	
	public Message() {
		
	}

	public static String getWeilcomeMessage() {
		return WEILCOME_MESSAGE;
	}

	public static String getFinishMessage() {
		return FINISH_MESSAGE;
	}
	
	
	//����һ��Item������message
	public String pickUpItem(int number1,int number2){
		return "yeah, you pick it up, you  \n 	need find another "+number1+" cats and "+number2+
				" \n	birds"+", keep going !";
	}
	
	//����һ��Item������message
	public String pullDownItem(int number1,int number2){
		return "oh..., you put it down,now  \n 	 you need find "+number1+" cats and "+number2+
				" \n	birds, Again!";
	}
	
}
