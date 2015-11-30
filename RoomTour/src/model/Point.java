package model;

/**
 * Point类：以一个Point为视点，包含左 左 前 右 右 5个方向图片
 *	需要注意转向动作是在Point上进行的，转向并不改变站定的Point，而前后移动则需要改变站定的Point
 * @author  Song Ya rong;
 * @version 1.0
 */

public class Point {
	
	private static final int MAX_DIRECTION = 5;//暂定一个Point有左 左 前 右 右 5张图片
	private int nowDirection = MAX_DIRECTION/2;//记录当前Point转过的方向（初始化看到是最中间一张）
	private String[] imageSet = new String[MAX_DIRECTION];//当前Point所需要的图片集
	
	//构造函数
	public Point(){
		
	}
	
	public Point(String[] images) {
		this.setImageSet(images);
	}
	
	//Set Get 函数
	public int getNowDirection() {
		return nowDirection;
	}
	public void setNowDirection(int nowDirection) {
		this.nowDirection = nowDirection;
	}
	public String[] getImageSet() {
		return imageSet;
	}
	public void setImageSet(String[] imageSet) {
		this.imageSet = imageSet;
	}
	public static int getMaxDirection() {
		return MAX_DIRECTION;
	}
	
	
	//判断能否左右转向，如果下一个方向已经超出我们预设的左右值的范围(存储照片的数组界限)，即不能转向
	public boolean canLeft(){
		int nextDirection = this.getNowDirection()-1;
		if(nextDirection<0)
			return false;
		return true;
	}
	
	public boolean canRight(){
		int nextDirection = this.getNowDirection()+1;
		if(nextDirection>MAX_DIRECTION-1)
			return false;
		return true;
	}
	
	//左右转向操作，如果可以转向，则将现在的方向向左或向右偏移(对应左：-1，右：+1)
	public void turnLeft(){
		if(canLeft())
			this.setNowDirection(nowDirection-1);
	}
	
	public void turnRight(){
		if(canRight())
			this.setNowDirection(nowDirection+1);
	}
	
	//得到当前所在方向图像
	public String nowPicture(){
		return imageSet[nowDirection];
	}
	
}
