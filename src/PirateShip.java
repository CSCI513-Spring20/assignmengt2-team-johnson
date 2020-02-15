import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

public class PirateShip implements Observer
{
	public Point piratePosition = new Point();
	public Point shipPosition = new Point();
	public int[][] oceanGrid;
	
	public PirateShip(int x, int y, int[][] oceanGrid) {
		piratePosition.x = x;
		piratePosition.y = y;
		this.oceanGrid = oceanGrid;
	}
	
	public Point getShipLocation()
	{
		System.out.println("px: " + piratePosition.x + " | py: " + piratePosition.y);
		return piratePosition;
	}


	public void update(Observable o, Object arg1) 
	{
		if (o instanceof Ship)
		{
			shipPosition = ((Ship)o).getShipLocation();
			move();
		}
		
	}
	
	public void move()
	{
		System.out.println("MOVING");
		System.out.println("shipx: " + shipPosition.getLocation().x + " | shipy: " + shipPosition.getLocation().y);
		System.out.println("pShipx: " + piratePosition.getLocation().x + " | pShipy: " + piratePosition.getLocation().y);
		
		if (Math.abs(shipPosition.getLocation().x - piratePosition.getLocation().x) >= Math.abs(shipPosition.getLocation().y - piratePosition.getLocation().y))
		{
			if (shipPosition.getLocation().x > piratePosition.getLocation().x) piratePosition.x = piratePosition.x + 1;
			else piratePosition.x = piratePosition.x - 1;
		}
		else if (Math.abs(shipPosition.getLocation().x - piratePosition.getLocation().x) < Math.abs(shipPosition.getLocation().y - piratePosition.getLocation().y))
		{
			if (shipPosition.getLocation().y > piratePosition.getLocation().y) piratePosition.y = piratePosition.y + 1;
			else piratePosition.y = piratePosition.y - 1;
		}
	}

}
