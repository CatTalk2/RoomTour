package model;

/**
 * Room�ࣺһ��Room�ݶ���������Point������ǰ���ƶ���room�ڵ�point�ƶ�������Location�ڵ�room���ƶ��������н��ܣ�
 *
 * @author  Song Ya rong;
 * @version 1.0
 */

public class Room {
	
	private static final int MAX_POINT = 2;//�ݶ�һ��Room�������վ����
	private int nowPoint;//��¼��ǰ��վ���ĵ�
	private Point[] points = new Point[MAX_POINT];
	private String name;//��ʾRoom Name
	
	//���캯��
	public Room() {
		
	}
	
	public Room(String name,Point[] points){
		this.name = name;
		this.points = points;
		this.nowPoint = 0;//��ʼ����վ���ڵ�һ��Point
	}
	
	//Set Get ����
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

	//�ж��ܷ�ǰ��,ֻ������ǰ����room������һ��point��ʱ����ǰ������һ��point
	public boolean canForward(){
		int nexPoint = nowPoint+1;
		//��ʱ����������ǰ��ʱ����ǰ�����ߺ���
		if(nexPoint<MAX_POINT && (pointNow().getNowDirection()==Point.getMaxDirection()/2))
			return true;
		return false;
	}
	
	//�ж��ܷ����,ֻ������ǰ����room������һ��point��ʱ�����˻ص���һ��point
	public boolean canBack(){
		int lastPoint = nowPoint-1;
		if(lastPoint>=0 && (pointNow().getNowDirection()==Point.getMaxDirection()/2))
			return true;
		return false;
	}
	
	//ǰ�����ߺ��˲���
	public void moveForward(){
		if(canForward())
			this.setNowPoint(nowPoint+1);
	}
	
	public void moveBack(){
		if(canBack())
			this.setNowPoint(nowPoint-1);
	}
	
	//��ǰPoint
	public Point pointNow(){
		return points[nowPoint];
	}
	
}
