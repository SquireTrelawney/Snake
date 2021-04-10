import java.awt.Color;

public class Snake {
	Head myHead;
	int SIZE;
	Color color;
	String direction;
	
	public Snake() {
		myHead = new Head();
		SIZE = 3;
		color = Color.RED;
		direction = "left";
	}
	
	public void move() {
		if(direction == "left") {
			myHead.x--;
		}
		if(direction == "right") {
			myHead.x++;
		}
		if(direction == "up") {
			myHead.y--;
		}
		if(direction == "down") {
			myHead.y++;
		}
	}
	
}
