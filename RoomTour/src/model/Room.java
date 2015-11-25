package model;

/**
 * Room类：一个Room暂定包含两个Point，所以前后移动在room内的point移动，或者Location内的room间移动（后面有介绍）
 *
 * @author  Song Ya rong;
 * @version 1.0
 */

public class Room {
	
	private static final int MAX_POINT = 2;//暂定一个Room最多两个站定点
	private int nowPoint;//记录当前所站定的点
	private Point[] points = new Point[MAX_POINT];
	private String name;//标示Room Name
	
	//构造函数
	public Room() {
		
	}
	
	public Room(String name,Point[] points){
		this.name = name;
		this.points = points;
		this.nowPoint = 0;//初始化，站定在第一个Point
	}
	
	//Set Get 函数
	public int getNowPoint() {
		return nowPoint;
	}
	public void setNowPoint(int nowPoint) {
		this.nowPoint = nowPoint;
	}
	public Point[] getPoints() {
		return points;
	}
	public void setPoints(Point[] points) {
		this.points = points;
	}
	public static int getMaxPoint() {
		return MAX_POINT;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//判断能否前进,只有面向前方且room内有下一个point点时才能前进到下一个point
	public boolean canForward(){
		int nexPoint = nowPoint+1;
		//暂时决定面向正前方时可以前进或者后退
		if(nexPoint<MAX_POINT && (pointNow().getNowDirection()==Point.getMaxDirection()/2))
			return true;
		return false;
	}
	
	//判断能否后退,只有面向前方且room内有上一个point点时才能退回到上一个point
	public boolean canBack(){
		int lastPoint = nowPoint-1;
		if(lastPoint>=0 && (pointNow().getNowDirection()==Point.getMaxDirection()/2))
			return true;
		return false;
	}
	
	//前进或者后退操作
	public void moveForward(){
		if(canForward())
			this.setNowPoint(nowPoint+1);
	}
	
	public void moveBack(){
		if(canBack())
			this.setNowPoint(nowPoint-1);
	}
	
	//当前Point
	public Point pointNow(){
		return points[nowPoint];
	}
	
}
