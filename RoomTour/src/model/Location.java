package model;

/**
 * Location类：重点注意判断能否从当前room前进到下一个room或者后退到上一个room
 * 暂定3个room
 *	
 * @author  Song Ya rong;
 * @version 1.0
 */

public class Location {
	
	private static final int MAX_ROOM = 3;//暂定最多3个Room
	private Room[] rooms = new Room[MAX_ROOM];
	private int nowRoom;//记录当前所在的Room
	
	//构造函数
	public Location() {
		
	}
	
	public Location(Room[] rooms){
		this.rooms = rooms;
		nowRoom = 0;
	}
	
	
	//Set Get函数
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
	
	
	//判断能否前进到下一个房间或者后退到前一个房间
	public boolean canForward(){
		int nextRoom = nowRoom+1;
		//暂时决定在room内第二个Point最大方向(最右)且有下一个room时可以前进：这里跟你所拍的照片具体有关，不是固定算法
		if(nextRoom<MAX_ROOM && (roomNow().getNowPoint()==Room.getMaxPoint()-1) 
				&& (roomNow().pointNow().getNowDirection()==Point.getMaxDirection()-1))
			return true;
		return false;
	}
	
	public boolean canBack(){
		int lastRoom = nowRoom-1;
		//暂时决定在room内第一个Point方向且有上一个room时为向前时可以后退：这里跟你所拍的照片具体有关，不是固定算法
		if(lastRoom>=0 && (roomNow().getNowPoint()==0) 
				&& (roomNow().pointNow().getNowDirection()==Point.getMaxDirection()/2))
			return true;
		return false;
		
	}
	
	//前进到下一个房间或者后退到前一个房间
	public void moveForward(){
		if(canForward())
			this.setNowRoom(nowRoom+1);
	}
	
	public void moveBack(){
		if(canBack())
			this.setNowRoom(nowRoom-1);
	}
	
	
	//得到当前Room对象
	public Room roomNow(){
		return rooms[nowRoom];
	}
}
