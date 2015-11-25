package model;

/**
 * Point�ࣺ��һ��PointΪ�ӵ㣬������ �� ǰ �� �� 5������ͼƬ
 *	��Ҫע��ת��������Point�Ͻ��еģ�ת�򲢲��ı�վ����Point����ǰ���ƶ�����Ҫ�ı�վ����Point
 * @author  Song Ya rong;
 * @version 1.0
 */

public class Point {
	
	private static final int MAX_DIRECTION = 5;//�ݶ�һ��Point���� �� ǰ �� �� 5��ͼƬ
	private int nowDirection = MAX_DIRECTION/2;//��¼��ǰPointת���ķ��򣨳�ʼ�����������м�һ�ţ�
	private String[] imageSet = new String[MAX_DIRECTION];//��ǰPoint����Ҫ��ͼƬ��
	
	//���캯��
	public Point(){
		
	}
	
	public Point(String[] images) {
		this.setImageSet(images);
	}
	
	//Set Get ����
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
	
	
	//�ж��ܷ�����ת�������һ�������Ѿ���������Ԥ�������ֵ�ķ�Χ(�洢��Ƭ���������)��������ת��
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
	
	//����ת��������������ת�������ڵķ������������ƫ��(��Ӧ��-1���ң�+1)
	public void turnLeft(){
		if(canLeft())
			this.setNowDirection(nowDirection-1);
	}
	
	public void turnRight(){
		if(canRight())
			this.setNowDirection(nowDirection+1);
	}
	
	//�õ���ǰ���ڷ���ͼ��
	public String nowPicture(){
		return imageSet[nowDirection];
	}
	
}
