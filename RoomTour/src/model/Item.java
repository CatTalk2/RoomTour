package model;

/**
 * Item�ࣺ�ɼ�ȡ��Item
 *
 * @author  Song Ya rong;
 * @version 1.0
 */


public class Item {
	
	//�������ԣ�name�� image���Ƿ�pick up
	private String itemName;
	private String itemImage;
	private boolean isPickUP;
	
	public Item() {
		
	}
	
	public Item(String name,String image){
		this.itemName = name;
		this.itemImage = image;
		this.isPickUP = false;
	}
	
	public String getName() {
		return itemName;
	}
	public void setName(String Name) {
		this.itemName = Name;
	}
	public String getImage() {
		return itemImage;
	}
	public void setImage(String Image) {
		this.itemImage = Image;
	}

	public boolean isPickUP() {
		return isPickUP;
	}

	public void setPickUP(boolean isPickUP) {
		this.isPickUP = isPickUP;
	}
	
}
