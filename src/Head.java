import java.awt.Color;

public class Head {
	int x, y;
	Color color;
	public Head() {
		x = y = 5;
		color = Color.GREEN;
		
	}
	public boolean checkOutOfBounds() {
		if(x < 0 || x > 9 || y < 0 || y > 9) {
			return true;
		}else {
			return false;
		}
	}
}
