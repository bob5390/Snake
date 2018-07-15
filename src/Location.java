
public class Location {
	int x;
	int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Location(Location target) {
		this.x = target.getX();
		this.y = target.getY();
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Location getXY() {
		return new Location(x, y);
	}
	
	public void setX(int nx) {
		x = nx;
	}
	
	public void setY(int ny) {
		y = ny;
	}
	
	public void setLocation(Location targetLocation) {
		x = targetLocation.getX();
		y = targetLocation.getY();
	}
	
	public void addX(int dx) {
		x += dx;
	}
	
	public void addY(int dy) {
		y += dy;
	}
	
	public void addLocation(Location loc) {
		x += loc.getX();
		y += loc.getY();
	}
	
	public double getDistToLocation(Location targetLocation) {
		double dist = Math.sqrt(Math.pow(x - targetLocation.getX(), 2) + Math.pow(y - targetLocation.getY(), 2));
		return dist;
	}
	
	public boolean isEqual(Location target) {
		if(target.getX() == this.x && target.getY() == this.y) return true;
		else return false;
	}
}
