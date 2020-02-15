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
		System.out.println("x: " + currentLocation.x + " | y: " + currentLocation.y);
		return currentLocation;
	}
	
	public void goEast() {
		currentLocation.x = currentLocation.x + 1;
	}
	
	public void goWest() {
		currentLocation.x = currentLocation.x - 1;
	}
	
	public void goNorth() {
		currentLocation.y = currentLocation.y - 1;
	}
	
	public void goSouth() {
		currentLocation.y = currentLocation.y + 1;
	}
}
