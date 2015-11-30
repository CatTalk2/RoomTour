package model;

/**
 * Location�ࣺ�ص�ע���ж��ܷ�ӵ�ǰroomǰ������һ��room���ߺ��˵���һ��room
 * �ݶ�3��room
 *	
 * @author  Song Ya rong;
 * @version 1.0
 */

public class Location {
	
	private static final int MAX_ROOM = 3;//�ݶ����3��Room
	private Room[] rooms = new Room[MAX_ROOM];
	private int nowRoom;//��¼��ǰ���ڵ�Room
	
	//���캯��
	public Location() {
		
	}
	
	public Location(Room[] rooms){
		this.rooms = rooms;
		nowRoom = 0;
	}
	
	
	//Set Get����
	public Room[] getRooms() {
		return rooms;
	}

	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}

	public int getNowRoom() {
		return nowRoom;
	}

	public void setNowRoom(int nowRoom) {
		this.nowRoom = nowRoom;
	}

	public static int getMaxRoom() {
		return MAX_ROOM;
	}
	
	
	//�ж��ܷ�ǰ������һ��������ߺ��˵�ǰһ������
	public boolean canForward(){
		int nextRoom = nowRoom+1;
		//��ʱ������room�ڵڶ���Point�����(����)������һ��roomʱ����ǰ��������������ĵ���Ƭ�����йأ����ǹ̶��㷨
		if(nextRoom<MAX_ROOM && (roomNow().getNowPoint()==Room.getMaxPoint()-1) 
				&& (roomNow().pointNow().getNowDirection()==Point.getMaxDirection()-1))
			return true;
		return false;
	}
	
	public boolean canBack(){
		int lastRoom = nowRoom-1;
		//��ʱ������room�ڵ�һ��Point����������һ��roomʱΪ��ǰʱ���Ժ��ˣ�����������ĵ���Ƭ�����йأ����ǹ̶��㷨
		if(lastRoom>=0 && (roomNow().getNowPoint()==0) 
				&& (roomNow().pointNow().getNowDirection()==Point.getMaxDirection()/2))
			return true;
		return false;
		
	}
	
	//ǰ������һ��������ߺ��˵�ǰһ������
	public void moveForward(){
		if(canForward())
			this.setNowRoom(nowRoom+1);
	}
	
	public void moveBack(){
		if(canBack())
			this.setNowRoom(nowRoom-1);
	}
	
	
	//�õ���ǰRoom����
	public Room roomNow(){
		return rooms[nowRoom];
	}
}
