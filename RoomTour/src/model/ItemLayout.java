package model;

/**
 * ItemLayout�ࣺ�򵥵ĸ����࣬Ϊ�˵���Item���õ�λ��
 *
 * @author  Song Ya rong;
 * @version 1.0
 */


public class ItemLayout {
	
	//Ĭ��λ��
	private double layoutX ;
	private double layoutY ;
	
	public ItemLayout(){
		//Ĭ��λ��(200,330)
		this.layoutX = 200;
		this.layoutY = 330;
	}
	
	public ItemLayout(double x,double y) {
		this.layoutX = x;
		this.layoutY = y;
	}

	public double getLayoutX() {
		return layoutX;
	}

	public void setLayoutX(double layoutX) {
		this.layoutX = layoutX;
	}

	public double getLayoutY() {
		return layoutY;
	}

	public void setLayoutY(double layoutY) {
		this.layoutY = layoutY;
	}
	
	

}
