import java.awt.Point;

public class Ship 
{
	Point currentLocation = new Point();
	
	public Ship(int X, int Y) 
	{
		currentLocation.x = X;
		currentLocation.y = Y;
	}
	
	public Point getShipLocation() 
	{
		return currentLocation;
	}
	
	public void goEast() {
		if (currentLocation.x < 9) currentLocation.x = currentLocation.x + 1;
	}
	
	public void goWest() {
		if (currentLocation.x > 0) currentLocation.x = currentLocation.x - 1;
	}
	
	public void goNorth() {
		if (currentLocation.y > 0) currentLocation.y = currentLocation.y - 1;
	}
	
	public void goSouth() {
		if (currentLocation.y < 9) currentLocation.y = currentLocation.y + 1;
	}
}
