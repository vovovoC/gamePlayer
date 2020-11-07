import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
public class MyPlayer implements Player {
	private Circle ball;		
	private Map map;
	private Position position;
	public MyPlayer(Map map){
		this.map = map;
		ballPlayer();
		position = map.getStartPosition();
	}
	public Map getMap(){ return map; }
	public Circle getBall(){ return ball; }

	public void ballPlayer(){		
		ball = new Circle();
		ball.setRadius(20); // this a my player
					ball.setCenterX((map.getStartPosition().getX()*22)+25);
					ball.setCenterY((map.getStartPosition().getY()*22)+25);    // to find start position center
					ball.setFill(Color.RED);
					ball.setStrokeWidth(1);
					ball.setStroke(Color.BLACK);
					map.pane.add(ball,map.getStartPosition().getY(),map.getStartPosition().getX());
	}				
	
	@Override
	public void moveRight(){
		try{
			int x = position.getX();
			int y = position.getY();
			if((map.getValueAt(x,(y+1)) !=1) && (y+1) < map.getSize()){
				map.pane.getChildren().remove(ball);    // removes ball then creates position in the next cell
				map.pane.add(ball,(y+1),x);
				position = new Position(x, (y+1));
			}else{ 
				System.out.println("Invalid position!"); 
			}
		}catch(Exception e){
			System.out.println("invalid position");	
		}	
	}
	@Override
	public void moveLeft(){	
		try{
			int x = position.getX();
			int y = position.getY();
			if((map.getValueAt(x,(y-1)) !=1) && y>0){
				map.pane.getChildren().remove(ball);
				map.pane.add(ball,(y-1),x);
				position = new Position(x, (y-1));
			}else{ 
				System.out.println("Invalid position!"); 
			}
		}catch(Exception e){
			System.out.println("invalid position");	
		}	
	}
	@Override
	public void moveUp(){
		try{
			int x = position.getX();
			int y = position.getY();
			if((map.getValueAt((x-1),y) !=1) &&  x>0){
				map.pane.getChildren().remove(ball);
				map.pane.add(ball,y,(x-1));
				position = new Position((x-1),y);
			}
			else{ 
				System.out.println("Invalid position!"); 
			}
		}catch(Exception e){
			System.out.println("invalid position");	
		}	
	}
	@Override
	public void moveDown(){	
	try{
			int x = position.getX();
			int y = position.getY();
			if((map.getValueAt((x+1),y) !=1) &&  (x+1)< map.getSize()){
				map.pane.getChildren().remove(ball);
				map.pane.add(ball,y,(x+1));
				position = new Position((x+1),y);
			}else{ 
				System.out.println("Invalid position!"); 
			}
		}catch(Exception e){
			System.out.println("invalid position");	
		}	
	}
	@Override
	public Position getPosition(){
		return this.position;
	}
}