package model;

/**
 * ItemLayout类：简单的辅助类，为了调整Item放置的位置
 *
 * @author  Song Ya rong;
 * @version 1.0
 */


public class ItemLayout {
	
	//默认位置
	private double layoutX ;
	private double layoutY ;
	
	public ItemLayout(){
		//默认位置(200,330)
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
