import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel {
	Snake mySnake;
	final int SIZE = 10;
	int[][] matrix;
	Timer tm, appleTm;
	Apple apple;
	FileWorker fileWorker;
	String bestScore, endMessage;
	
	public Panel() {
		fileWorker = new FileWorker();
		bestScore = fileWorker.read();
		mySnake = new Snake();
		matrix = new int[SIZE][SIZE];
		endMessage = "";
		matrix[mySnake.myHead.x][mySnake.myHead.y] = 1;
		JButton BStart = new JButton("Start");
		BStart.setBounds(450, 50, 100, 50);
		add(BStart);
		BStart.setVisible(true);

		BStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				restart();
			}
		});
		BStart.setFocusable(false);
		setLayout(null);

		tm = new Timer(1000 / 2, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mySnake.move();
				if (mySnake.myHead.x > SIZE - 1 || mySnake.myHead.x < 0 || mySnake.myHead.y > SIZE - 1
						|| mySnake.myHead.y < 0  || matrix[mySnake.myHead.x][mySnake.myHead.y] > 0) {
					endMessage = "Вы проиграли!";
					tm.stop();
				} else if (mySnake.SIZE >= 100) {
					endMessage = "Вы победили!!!";
					tm.stop();
				} else {
					if (matrix[mySnake.myHead.x][mySnake.myHead.y] < 0) {
						mySnake.SIZE = mySnake.SIZE - matrix[mySnake.myHead.x][mySnake.myHead.y];
						updateBestScore();
					}
					matrix[mySnake.myHead.x][mySnake.myHead.y] = 1;
					
				}
				repaint();
			}
		});
		tm.start();

		appleTm = new Timer(5000 / 1, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				do {
					apple = new Apple();		
				}while(matrix[apple.x][apple.y] != 0 && mySnake.SIZE < 100);
				matrix[apple.x][apple.y] = apple.SIZE;
			}
		});
		appleTm.start();

	}

	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < SIZE + 1; i++) {
			g.drawLine(50 + i * 30, 50, 50 + i * 30, SIZE * 30 + 50);
			g.drawLine(50, 50 + i * 30, SIZE * 30 + 50, 50 + i * 30);
		}
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (matrix[i][j] == 1) {
					g.setColor(mySnake.myHead.color);
					g.fillRect(50 + i * 30, 50 + j * 30, 30, 30);
				}
				if (matrix[i][j] > 1) {
					g.setColor(mySnake.color);
					g.fillRect(50 + i * 30, 50 + j * 30, 30, 30);
				}
				if (matrix[i][j] > 0) {
					matrix[i][j]++;
				}
				if (matrix[i][j] < 0) {
					g.setColor(apple.color);
					g.fillRect(50 + i * 30, 50 + j * 30, 30, 30);
				}

				if (matrix[i][j] > mySnake.SIZE) {
					matrix[i][j] = 0;
				}
			}

		}
		g.setColor(Color.BLACK);
		g.drawString("Лучший счёт: " + bestScore, 10, 10);
		g.drawString(endMessage, 450, 150);
		

	}

	public void restart() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				matrix[i][j] = 0;
			}
		}
		mySnake.SIZE = 3;
		mySnake.myHead.x = 5;
		mySnake.myHead.y = 5;
		endMessage = "";
		repaint();
		tm.restart();
		appleTm.restart();
	}

	public void updateBestScore() {
		if (mySnake.SIZE > Integer.parseInt(bestScore)) {
			fileWorker.write(mySnake.SIZE);
			bestScore = String.valueOf(mySnake.SIZE);
		}
	}

}
