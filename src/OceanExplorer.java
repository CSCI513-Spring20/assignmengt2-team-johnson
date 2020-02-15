import java.util.Random;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class OceanExplorer extends Application {
	
	OceanMap oceanMap = new OceanMap();
	AnchorPane myPane = new AnchorPane();
	Scene scene = new Scene(myPane, oceanMap.scale*oceanMap.dimension, oceanMap.scale*oceanMap.dimension);
	Image shipImage;
	ImageView shipImageView;
	int[][] oceanGrid = oceanMap.getMap();
	Random rand = new Random();
	int rand_x = rand.nextInt(oceanMap.dimension);
	int rand_y = rand.nextInt(oceanMap.dimension);
	Ship ship = new Ship(rand_x, rand_y);
	public enum OceanItems
	{
		OCEAN(0),
		ISLAND(1),
		PIRATE(2);
		
		public final int intValue;
		OceanItems(int intValue)
		{
			this.intValue = intValue;
		}
		
		public int getIntValue() { return intValue; }
	}
	
	
	public void start(Stage oceanStage) throws Exception {
		
		oceanStage.setScene(scene);
		oceanStage.setTitle("Christopher Columbus Game");
		oceanStage.show();
		drawMap();
		placeIsland(10);
		if (oceanGrid[ship.currentLocation.x][ship.currentLocation.y] != OceanItems.OCEAN.getIntValue())
		{
			while(oceanGrid[ship.currentLocation.x][ship.currentLocation.y] != OceanItems.OCEAN.getIntValue())
			{
				rand_x = rand.nextInt(oceanMap.dimension);
				rand_y = rand.nextInt(oceanMap.dimension);
				ship.currentLocation.x = rand_x;
				ship.currentLocation.y = rand_y;
			}	
		}
		loadShipImage();
		startSailing();
		for (int x = 0; x < oceanMap.dimension; x++) {
			for (int y = 0; y < oceanMap.dimension; y++) {
				System.out.print(oceanGrid[x][y] + " | ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void drawMap() {
		for (int x = 0; x < oceanMap.dimension; x++) {
			for (int y = 0; y < oceanMap.dimension; y++) {
				oceanGrid[x][y] = OceanItems.OCEAN.getIntValue();
				Rectangle rect = new Rectangle(x*oceanMap.scale, y*oceanMap.scale, oceanMap.scale, oceanMap.scale);
				rect.setStroke(Color.BLACK);
				rect.setFill(Color.AQUAMARINE);
				myPane.getChildren().add(rect);
			}
		}
	}
	
	public void placeIsland(int num)
	{
		Random rand = new Random();
		for (int i = 0; i < num; i++)
		{
			rand_x = rand.nextInt(oceanMap.dimension);
			rand_y = rand.nextInt(oceanMap.dimension);
			oceanGrid[rand_x][rand_y] = OceanItems.ISLAND.getIntValue();
			Rectangle rect = new Rectangle(rand_x*oceanMap.scale, rand_y*oceanMap.scale, oceanMap.scale, oceanMap.scale);
			rect.setStroke(Color.BLACK);
			rect.setFill(Color.DARKGREEN);
			myPane.getChildren().add(rect);
		}
	}
	
	public void loadShipImage() {
		shipImage = new Image("ship.png", 50, 50, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(ship.getShipLocation().x * oceanMap.scale);
		shipImageView.setY(ship.getShipLocation().y * oceanMap.scale);
		myPane.getChildren().add(shipImageView);
	}
	
	private void startSailing() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				switch(ke.getCode())
				{
					case RIGHT:
						if (ship.getShipLocation().x < oceanMap.dimension-1) 
						{
							if (oceanGrid[ship.getShipLocation().x+1][ship.getShipLocation().y] == OceanItems.OCEAN.getIntValue()) ship.goEast();	
						}
						break;
					case LEFT:
						if (ship.getShipLocation().x > 0) 
						{
							if (oceanGrid[ship.getShipLocation().x-1][ship.getShipLocation().y] == OceanItems.OCEAN.getIntValue()) ship.goWest();
						}
						break;
					case UP:
						if (ship.getShipLocation().y > 0) {
							if (oceanGrid[ship.getShipLocation().x][ship.getShipLocation().y-1] == OceanItems.OCEAN.getIntValue()) ship.goNorth();
						}
						break;
					case DOWN:
						if (ship.getShipLocation().y < oceanMap.dimension-1)
						{
							if (oceanGrid[ship.getShipLocation().x][ship.getShipLocation().y+1] == OceanItems.OCEAN.getIntValue()) ship.goSouth();	
						}
						break;
					default:
						break;
				}
				shipImageView.setX(ship.getShipLocation().x * oceanMap.scale);
				shipImageView.setY(ship.getShipLocation().y * oceanMap.scale);
			}
		});	
	}

}