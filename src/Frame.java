
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame extends JFrame {
	Panel myPanel;
	
	public Frame() {
		myPanel = new Panel();
		
		add(myPanel);
		addKeyListener(new MyKey());
		//setExtendedState(MAXIMIZED_BOTH);
		setBounds(0,0,600,600);
		setVisible(true);
		setFocusable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	class MyKey implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == e.VK_A && myPanel.mySnake.direction != "right") {
				myPanel.mySnake.direction = "left";
			}
			if(e.getKeyCode() == e.VK_W && myPanel.mySnake.direction != "down") {
				myPanel.mySnake.direction = "up";
			}
			if(e.getKeyCode() == e.VK_D && myPanel.mySnake.direction != "left") {
				myPanel.mySnake.direction = "right";
			}
			if(e.getKeyCode() == e.VK_S && myPanel.mySnake.direction != "up") {
				myPanel.mySnake.direction = "down";
			}
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}
