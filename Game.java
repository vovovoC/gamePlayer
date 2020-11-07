import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.animation.FadeTransition;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.animation.Timeline;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.animation.ParallelTransition;
import javafx.scene.text.TextAlignment;
public class Game extends Application{
	private Map map;
	private MyPlayer player;
	private Food food;
	public static void main(String[] args){ launch(args); }
      @Override
    public void start(Stage stage) throws Exception{
    	GridPane w = new GridPane(); 	w.setStyle("-fx-background-color: black;");   

	 Rectangle rectanleAnimation = new Rectangle (100,25,90,90);
		 rectanleAnimation.setStroke(Color.WHITE);
	     rectanleAnimation.setFill(Color.VIOLET);
	     rectanleAnimation.setArcHeight(50);
	     rectanleAnimation.setArcWidth(50);
	     rectanleAnimation.setStrokeWidth(3);
    
	     final Duration DUR_TIME_3=Duration.millis(4000);		// creates millisecond resolution 
	     final Duration DUR_TIME_1=Duration.millis(2500);

     FadeTransition fade = new FadeTransition(DUR_TIME_3);             TranslateTransition translate = new TranslateTransition(DUR_TIME_1);
	     fade.setFromValue(1.0);											 translate.setFromX(-100);
	     fade.setToValue(0.3);												 translate.setToX(100);
	     fade.setCycleCount(2);												 translate.setCycleCount(2);
	     fade.setAutoReverse(true);											 translate.setAutoReverse(true);
     // FadeTransition -> creates fade effect that spans its duration
	 // TranslateTransition -> mooves animation to another place   
	  	    
    ScaleTransition scale = new ScaleTransition(DUR_TIME_1);	      RotateTransition rotate = new RotateTransition(DUR_TIME_3);
	    scale.setByX(1.5); 						  							rotate.setByAngle(180);
	    scale.setByY(1.5);													rotate.setCycleCount(4);
	    scale.setCycleCount(2);												rotate.setAutoReverse(true);
      	scale.setAutoReverse(true);
	 //Scale Transition ->  creates scale animmation                  //Rotate-> rotates animation  
	    
     ParallelTransition parallel = new ParallelTransition(rectanleAnimation,fade,translate,rotate,scale); // allow to do many animation parallel
    		parallel.play();
	     	
			DropShadow shadow = new DropShadow();  // creates shadow to text
			shadow.setOffsetY(5.0);
			shadow.setColor(Color.color(0.5, 0.5, 0.5));
			 
			Text text = new Text(); 	 // creates text on pane
				text.setEffect(shadow);  //effect of a text
				//text.setCache(true);     //
				text.setFill(Color.WHITE);
				text.setText("W E L C O M E");
				text.setFont(Font.font(null, FontWeight.BOLD, 28)); // style and size of a text

			w.getChildren().addAll(rectanleAnimation,text);
			w.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(w,400,400);
	    	stage.setScene(scene1);
	    	stage.setTitle("_G_A_M_E_");
	    	stage.show();
    	scene1.setOnKeyPressed(e -> {
    		if(e.getCode() == KeyCode.ENTER){  //allows to manipulate with keyboard
    			stage.close();
    			myGame();
    		}
    	});
    }
 	public void myGame(){
 		Stage primaryStage = new Stage();
 		map = new Map("./map1.txt");  //initialize Map class
    	map.readFile();				// read given text file
    	player = new MyPlayer(map);	//initialize MyPlayer class
    	food = new Food(map,player);
		Scene scene = new Scene(map.drawPane(),400,400);	
		scene.setOnKeyPressed(ex->{
    			if(ex.getCode() == KeyCode.RIGHT){  player.moveRight();  } // allows to manipulate with keyboard
    			if(ex.getCode() == KeyCode.LEFT) {  player.moveLeft();   } 
    			if(ex.getCode() == KeyCode.UP)   {  player.moveUp();  	 }
    			if(ex.getCode() == KeyCode.DOWN) {  player.moveDown();   }
    	});


		primaryStage.setScene(scene);
		primaryStage.show();
 	}

}