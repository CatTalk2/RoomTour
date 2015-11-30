package controller;


import java.util.HashMap;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Item;
import model.ItemLayout;
import model.Location;
import model.Message;
import model.Point;
import model.Room;


/**
 * Controller类：逻辑控制
 *
 * @author  Song Ya rong;
 * @version 1.0
 */


public class TourController {
	
	Location location;
	
	//可声明多个，添加到对应位置即可
	Item item1 = new Item("cat","/image/item/cat.png");
	Item item2 = new Item("bird","/image/item/bird.png");
	
	//要用到的几个常量:MAX_CATS:场景中遗留的CAT; CATH_CATS:已经抓到的CAT，Bird同理
	String key;
	int MAX_CATS,MAX_BIRDS,CATH_BIRDS=0,CATH_CATS=0;
	
	//某个Room的某个Point的某个Direction(本质上是一张图片)上有一个Item，所以<String,Item>
	private HashMap<String, Item> catInfo = new HashMap<>();
	private HashMap<String, Item> birdInfo = new HashMap<>();
	
	//具体对应在这个Direction上的位置，
	private HashMap<String,ItemLayout> catLayout =new HashMap<>();//调整Cat在图片中的位置，为了看起来自然些
	private HashMap<String,ItemLayout> birdLayout =new HashMap<>();//调整Bird在图片中的位置，为了看起来自然些
	
	//三个Room，6个Point，同时声明一个Message对象，用于更新对话框内容
	Point[] points = new Point[6];
	Room[] rooms = new Room[3];
	Message message = new Message();
	
	//获得FXML控件
	@FXML private ImageView imageView;
	@FXML private ImageView catImage;
	@FXML private ImageView birdImage;
	@FXML private ImageView imageMap;
	@FXML private ImageView defaultImage;
	@FXML private ImageView defaultBirdImage;
	@FXML private ImageView catCage;
	@FXML private ImageView birdCage;
	
	@FXML private Button btnLeft;
	@FXML private Button btnRight;
	@FXML private Button btnForward;
	@FXML private Button btnBack;
	
	@FXML private Button btnPick;
	@FXML private Button btnPut;
	
	@FXML private ComboBox<String> comboBox;
	@FXML private TextArea messageText;
	@FXML private TextField tdDone;
	@FXML private Button btnSubmit;
	
	@FXML private Label catNumber;
	@FXML private Label anotherNumber;
	@FXML private Label birdNumber;
	
	//初始化：设置数据，显示当前Image
	public void Initialise(){
		setData();
		nowImage();
	}
	
	//设置一些初始化数据，此方法也可以在View类中实现
	public void setData(){
		//初始化一个Points(目前3个room，每个room2个point)
		String[] imageSet0 = new String[5];
		String[] imageSet1 = new String[5];
		String[] imageSet2 = new String[5];
		String[] imageSet3 = new String[5];
		String[] imageSet4 = new String[5];
		String[] imageSet5 = new String[5];
		
		//初始化图片集合
		for(int i=0;i<imageSet0.length;i++){
			imageSet0[i]="/image/drawingroom/"+i+".jpg";
			imageSet1[i]="/image/drawingroom/"+(i+5)+".jpg";
			imageSet2[i]="/image/kidsroom/"+i+".jpg";
			imageSet3[i]="/image/kidsroom/"+(i+5)+".jpg";
			imageSet4[i]="/image/bedroom/"+i+".jpg";
			imageSet5[i]="/image/bedroom/"+(i+5)+".jpg";
			
		}
		
		//初始化每个Room的Points
		Point[] drPoints = new Point[2];
		drPoints[0] = new Point(imageSet0);
		drPoints[1] = new Point(imageSet1);
		Point[] krPoints = new Point[2];
		krPoints[0] = new Point(imageSet2);
		krPoints[1] = new Point(imageSet3);
		Point[] brPoints = new Point[2];
		brPoints[0] = new Point(imageSet4);
		brPoints[1] = new Point(imageSet5);
		
		//初始化room
		Room bedRoom = new Room("BedRoom",brPoints);
		Room kidsRoom =new Room("KidsRoom",krPoints);
		Room drawingRoom =  new Room("DrawingRoom",drPoints);
		
		rooms[0] = drawingRoom;
		rooms[1] = kidsRoom;
		rooms[2] = bedRoom;
		
		//设置有cat及对应位置，注意这里要跟catLayout一一对应，每添加一个Item，都有添加对应的ItemLayout
		catInfo.put(imageSet0[0], item1);
		catInfo.put(imageSet1[4], item1);
		catInfo.put(imageSet2[4], item1);
		catInfo.put(imageSet4[0], item1);
		catLayout.put(imageSet0[0], new ItemLayout());
		catLayout.put(imageSet1[4], new ItemLayout(400,330));
		catLayout.put(imageSet2[4], new ItemLayout());
		catLayout.put(imageSet4[0], new ItemLayout());
		
		//设置有bird及其位置，注意这里要跟birdLayout一一对应
		birdInfo.put(imageSet0[4], item2);
		birdInfo.put(imageSet1[1], item2);
		birdInfo.put(imageSet3[4], item2);
		birdLayout.put(imageSet0[4], new ItemLayout(720,400));
		birdLayout.put(imageSet1[1], new ItemLayout(600,400));
		birdLayout.put(imageSet3[4], new ItemLayout(400,400));
		
		//目前场景中我们需要捕获的CAT和BIRD数目
		MAX_CATS = catInfo.size();
		MAX_BIRDS = birdInfo.size();
		
		//最右边静态显示的cat 和 bird图片
		defaultImage.setImage(new Image(item1.getImage()));
		defaultBirdImage.setImage(new Image(item2.getImage()));
		
		//笼子右边显示数目的Label，及右边统计还剩多少需要捕获的Label控件的属性设置
		catNumber.setTextFill(Color.web("#019858"));
	    catNumber.setFont(new Font("Arial", 18));
	    birdNumber.setTextFill(Color.web("#019858"));
	    birdNumber.setFont(new Font("Arial", 18));
	    anotherNumber.setTextFill(Color.web("#FF3399"));
	    anotherNumber.setFont(new Font("Arial", 15));
	    
	    //右下角用来控制bird的textField控件设置
	    tdDone.setPromptText("1: pick bird up 0: put it down");
	    tdDone.setPrefColumnCount(10);
	    
	    //下拉菜单初始化：设置值为当前room集合的names
		setComboBox();
		
		//得到location
		location = new Location(rooms);
		
		//TextArea显示欢迎message
		messageText.appendText("\n(｡◕ˇ∀ˇ◕）:"+message.getWeilcomeMessage()+"\n");
	}
	
	
	//公共方法，用来每次操作后刷新当前界面，把该更改的都改过来，所以更改函数最后都会调用此函数
	public void nowImage(){
		key = location.roomNow().pointNow().nowPicture();
		anotherNumber.setText("Need find another "+MAX_CATS+" Cats"+" "+MAX_BIRDS+" Birds");
		checkItem();
		checkMap();
		InitButton();
		
		Image image = new Image(location.roomNow().pointNow().nowPicture());
		imageView.setImage(image);
		//为了铺满，拉伸下(别的居中显示之类的API没找到)
		imageView.setScaleY(1.5);
		imageView.setScaleX(1.4);
	}
	
	//初始化按钮
	public void InitButton(){
		Image defaultImage = new Image("/image/application/default.png");
		btnPick.setGraphic(new ImageView(new Image("/image/application/pickup.png")));
		btnPut.setGraphic(new ImageView(new Image("/image/application/putdown.png")));
		btnLeft.setGraphic(new ImageView(defaultImage));
		btnRight.setGraphic(new ImageView(defaultImage));
		btnForward.setGraphic(new ImageView(defaultImage));
		btnBack.setGraphic(new ImageView(defaultImage));
			
		//当他们不可用时用别的图片代替，用来提醒不可点击
		if(location.roomNow().pointNow().canLeft())
			btnLeft.setGraphic(new ImageView(new Image("/image/application/left.png")));
		if(location.roomNow().pointNow().canRight())
			btnRight.setGraphic(new ImageView(new Image("/image/application/right.png")));
		if(location.canForward()||location.roomNow().canForward())
			btnForward.setGraphic(new ImageView(new Image("/image/application/top.png")));
		if(location.canBack()||location.roomNow().canBack())
			btnBack.setGraphic(new ImageView(new Image("/image/application/bottom.png")));
	}
	
	//检查当前位置是否放置了Item，如果是，显示出来
	public void checkItem(){
		Image mCat = new Image(item1.getImage());
		Image mBird = new Image(item2.getImage());
		//查看是否有cat
		if(catInfo.containsKey(key)){
			catImage.setLayoutX(catLayout.get(key).getLayoutX());
			catImage.setLayoutY(catLayout.get(key).getLayoutY());
			catImage.setImage(mCat);
		}
		else
			catImage.setImage(null);
		
		//查看是否有bird
		if(birdInfo.containsKey(key)){
			birdImage.setLayoutX(birdLayout.get(key).getLayoutX());
			birdImage.setLayoutY(birdLayout.get(key).getLayoutY());
			birdImage.setImage(mBird);
			
		}else
			birdImage.setImage(null);
		
		//更新笼子旁边数字
		if(CATH_CATS>0){
			catNumber.setText("X "+CATH_CATS);
			catCage.setImage(new Image("/image/application/catcage1.png"));
		}else{
			catNumber.setText("");
			catCage.setImage(new Image("/image/application/catcage.png"));
		}
		
		if(CATH_BIRDS>0){
			birdNumber.setText("X "+CATH_BIRDS);
			birdCage.setImage(new Image("/image/application/birdcage1.png"));
		}else{
			birdNumber.setText("");
			birdCage.setImage(new Image("/image/application/birdcage.png"));
		}
		
		//如果当前场景中所有的CAT和BIRD都被捕获了，则显示finish消息
		if((MAX_BIRDS==0) && (MAX_CATS==0)){
			messageText.appendText(" \n ｡◕‿◕｡ : "+message.getFinishMessage());
		}
	}
	
	//更新地图，换图即可(图片有限，我偷懒了)，按理来说，没个Point的每个direction对应一张Map
	public void checkMap(){
		Image image = null;
		int nMap = location.roomNow().pointNow().getNowDirection();
		switch(location.roomNow().getName()){
			case "DrawingRoom":
				image = new Image("/image/map/dr/"+nMap%5+".png");
				break;
			case "BedRoom":
				image = new Image("/image/map/br/"+nMap%5+".png");
				break;
			case "KidsRoom":
				image = new Image("/image/map/kr/"+nMap%5+".png");
				break;
		}
		imageMap.setImage(image);
	}
	
	//设置下拉框数据
	public void setComboBox(){
		for(int i =0; i<rooms.length;i++)
			comboBox.getItems().add(rooms[i].getName());
		comboBox.setValue(rooms[0].getName());
	}
	
	//改变下拉框时对应方法
	public void changeLocation(ActionEvent event){
		int now = 0;
		for(int i=0;i<rooms.length;i++){
			if(comboBox.getValue()==rooms[i].getName()){
				now = i;
				break;
			}
		}
		location.setNowRoom(now);
		messageText.appendText("\n(｡◕ˇ∀ˇ◕）:"+"You move to "+location.roomNow().getName()+"\n");
		nowImage();
	}
	
	//左键对应处理函数
	public void turnLeft(ActionEvent event){
		if(location.roomNow().pointNow().canLeft())
			location.roomNow().pointNow().turnLeft();
		else
			messageText.appendText("\n(╬￣皿￣) :"+" can't turn left\n");
		nowImage();
	}
	
	//右键对应处理函数
	public void turnRight(ActionEvent event){
		if(location.roomNow().pointNow().canRight())
			location.roomNow().pointNow().turnRight();
		else
			messageText.appendText("\n(╬￣皿￣) :"+" can't turn right\n");
		nowImage();
	}
	
	//前进或者后退需要注意是在本room内point上的前进后退还是，room之间的前进后退
	public void moveForward(ActionEvent event){
		if(location.roomNow().canForward()){
			location.roomNow().moveForward();
		}
		else if(location.canForward()){
			location.moveForward();
			comboBox.setValue(location.roomNow().getName());
			messageText.appendText("\n(｡◕ˇ∀ˇ◕）:"+"You forward to "+location.roomNow().getName()+"\n");
		}
		else{
			messageText.appendText("\n(╬￣皿￣) :"+" can't move Forward\n");
		}
		
		nowImage();
	}
	
	public void moveBack(ActionEvent event){
		if(location.roomNow().canBack()){
			location.roomNow().moveBack();
		}
		else if(location.canBack()){
			location.moveBack();
			comboBox.setValue(location.roomNow().getName());
			messageText.appendText("\n(｡◕ˇ∀ˇ◕）:"+"You back to "+location.roomNow().getName()+"\n");
		}
		else{
			messageText.appendText("\n(╬￣皿￣) :"+" can't move Back\n");
		}
		nowImage();
	}
	
	//对应button的pick up处理函数,这里只处理cat，bird逻辑与此相同，只是bird是通过textField控件对应方法处理
	public void pickUp(ActionEvent event){
		//查看当前位置是否有cat
		if(catInfo.containsKey(key)){
			catInfo.remove(key);
			MAX_CATS = MAX_CATS - 1;
			CATH_CATS = CATH_CATS + 1;
			messageText.appendText("\n(｡◕ˇ∀ˇ◕）:"+message.pickUpItem(MAX_CATS,MAX_BIRDS)+"\n");
		}else
			messageText.appendText("\n  ٩(͡๏̯͡๏)۶: 	"+"no cat to pick up \n");
		nowImage();
	}
	
	public void pullDown(ActionEvent event){
		//是否有可放置的cat
		if(CATH_CATS>0){
			if(catInfo.containsKey(key)){
				messageText.appendText("\n  ٩(͡๏̯͡๏)۶: 	"+" Why? no where to put it down \n");
			}else{
				catInfo.put(key, item1);
				catLayout.put(key, new ItemLayout());
				MAX_CATS = MAX_CATS + 1;
				CATH_CATS = CATH_CATS - 1;
				messageText.appendText("\n(｡◕ˇ∀ˇ◕）:"+message.pullDownItem(MAX_CATS,MAX_BIRDS)+"\n");
			}
		}else
			messageText.appendText("\n  ٩(͡๏̯͡๏)۶: 	"+"no cat to put down \n");
		nowImage();
	}
	
	//textField对应处理Pick up bird或者put down，逻辑与前面cat的pick up或者put down一模一样，代码有些重复
	public void Done(ActionEvent event){
		
		//如果是输入1或者0做相应处理
		if(tdDone.getText().equals(1+"")){
			if(birdInfo.containsKey(key)){
				birdInfo.remove(key);
				MAX_BIRDS = MAX_BIRDS - 1;
				CATH_BIRDS = CATH_BIRDS + 1;
				messageText.appendText("\n(｡◕ˇ∀ˇ◕）:"+message.pickUpItem(MAX_CATS,MAX_BIRDS)+"\n");
			}else
				messageText.appendText("\n  ٩(͡๏̯͡๏)۶: 	"+"no bird to pick up \n");
		}else if(tdDone.getText().equals(0+"")){
			if(CATH_BIRDS>0){
				if(birdInfo.containsKey(key)){
					messageText.appendText("\n  ٩(͡๏̯͡๏)۶: 	"+" Why? no where to put it down \n");
				}else{
					birdInfo.put(key, item1);
					birdLayout.put(key, new ItemLayout());
					MAX_BIRDS = MAX_BIRDS + 1;
					CATH_BIRDS = CATH_BIRDS - 1;
					messageText.appendText("\n(｡◕ˇ∀ˇ◕）:"+message.pullDownItem(MAX_CATS,MAX_BIRDS)+"\n");
				}
			}else
				messageText.appendText("\n  ٩(͡๏̯͡๏)۶: 	"+"no bird to put down \n");
		}else{
			messageText.appendText("\n  ٩(͡๏̯͡๏)۶: 	"+"You do nothing, try again\n"
					+ "	you should input 1 or 0\n");
		}
		tdDone.clear();
		nowImage();
	}
}
