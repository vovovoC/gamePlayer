import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.scene.Node;
public class Map extends Pane{
	private int UNIT;
	private int size;
	private int[][] map;
	private Position start;
	public String fileName;
	public GridPane pane = new GridPane();
	public Pane root;
	public Map(String fileName){
		this.fileName = fileName;
		this.UNIT = 40;
		readFile();
	}
	public int getUnit(){  return UNIT; }
	public int getSize(){  return size; }
	public Position getStart(){return start; }
	public int[][] getMap(){ return map; }
	public void setPosition(Position start){
		this.start = start;
	}
	public int getValueAt(int x, int y){
		return map[x][y];
	}
	public void readFile(){
		try{
			BufferedReader bf = new BufferedReader(new FileReader(fileName));
			String sizeFile = bf.readLine();
			size = Integer.parseInt(sizeFile);    // reads first line of a file then changes to take size of a map
			map = new int[size][size];
			for(int row = 0; row < size; row++){
				String[] readNextLine = bf.readLine().trim().split(" "); // reads next line then removes space, converts to array
				for(int column = 0; column < size; column++){
					map[row][column] = Integer.parseInt(readNextLine[column]); // fills 2d array
				}
			}
        } catch (IOException e) {
            e.printStackTrace();
        }	
	}
	public Position getStartPosition(){
		start = new Position(0,0);
		for(int i = 0; i < size;i++){
				for(int j = 0 ; j < size; j++){
					if(map[i][j] == 2){
						start = new Position(i,j); // returns start position where is "2"
					}
				}		
		}
		return start;
		}
	public Pane drawPane(){
		Pane mainPane = new Pane();
		int i = 0;
		while(i != size){
				RowConstraints rowTable = new RowConstraints(40);  //  makes table with grid pane column & row
		    	pane.getRowConstraints().add(rowTable);
		    	ColumnConstraints columnTable = new ColumnConstraints(40);
    			pane.getColumnConstraints().add(columnTable);   
    			i++; 
		}
		pane.setStyle("-fx-background-color: white; -fx-grid-lines-color: black");
		pane.setGridLinesVisible(true);  // allows to make visible rows & columns 
		for (int x = 0;x < size;x++){
			for(int y = 0 ;y < size; y++){
    			if(map[x][y] == 1){
    				Rectangle wall = new Rectangle(40,40); // draws reactangle walls
    				wall.setFill(Color.BLACK);
    				GridPane.setRowIndex(wall, x);
    				GridPane.setColumnIndex(wall,y);   // put it to given index
    				pane.getChildren().add(wall);
    				pane.setAlignment(Pos.CENTER);
    			}
			}
		}
		mainPane.getChildren().add(pane);
		mainPane.getChildren().addAll((Pane)getChildren().get(0));
	return mainPane;
	}
	@Override     
	public ObservableList<Node> getChildren(){ 
	    return super.getChildren();     
	}

}