/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import acmx.export.java.util.Set;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	public int no_bricks = 100;
	
	
/* Method: run() */
/** Runs the Breakout program. */
	
public void run() {
		set_paddle();
		set_bricks();
		set_ball();
		addMouseListeners();
		vy = 3;
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
		while(n < NTURNS){
			moveball();
			collision_check();
			
			if (getCollidingObject() == paddle) vy = -vy;
			else if(getCollidingObject()!= null){
				remove(getCollidingObject());
				vy = -vy;
				no_bricks--;
			}
			if(no_bricks == 0) {
				GLabel win = new GLabel("You Win", WIDTH/2, HEIGHT/2);
				add(win);
				break;
			}
			
			
			pause(10);
		}
		if (n==NTURNS){
			GLabel lose = new GLabel("you lose",WIDTH/2,HEIGHT/2);
			add(lose);
		}
	}
	
	private void set_bricks(){
		for(int i=0;i<NBRICK_ROWS;i++){	
			for(int j=0;j<NBRICKS_PER_ROW;j++){	
					int x = j*(BRICK_WIDTH + BRICK_SEP); 
					GRect brick = new GRect(x, BRICK_Y_OFFSET + i*(BRICK_HEIGHT+BRICK_SEP),BRICK_WIDTH,BRICK_HEIGHT);
					switch (i){
					case(0):	brick.setColor(Color.RED);
								brick.setFilled(true);
								break;
					case(1):	brick.setColor(Color.RED);
								brick.setFilled(true);
								break;
					case(2):	brick.setColor(Color.ORANGE);
								brick.setFilled(true);
								break;
					case(3):	brick.setColor(Color.ORANGE);
								brick.setFilled(true);
								break;
					case(4):	brick.setColor(Color.YELLOW);
								brick.setFilled(true);
								break;
					case(5):	brick.setColor(Color.YELLOW);
								brick.setFilled(true);
								break;
					case(6):	brick.setColor(Color.GREEN);
								brick.setFilled(true);
								break;
					case(7):	brick.setColor(Color.GREEN);
								brick.setFilled(true);
								break;
					case(8):	brick.setColor(Color.CYAN);
								brick.setFilled(true);
								break;
					case(9):	brick.setColor(Color.CYAN);
								brick.setFilled(true);
								break;
				}
				add(brick);
			}
		}
	}
	
	private void set_paddle(){
		paddle = new GRect((WIDTH-PADDLE_WIDTH)/2,HEIGHT-PADDLE_Y_OFFSET,PADDLE_WIDTH,PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
	}
	
	public void mousePressed(MouseEvent e) {
		lastX = e.getX();
	}
	
	public void mouseDragged(MouseEvent e) {
		 	
			 paddle.move(e.getX() - lastX, 0);
				lastX = e.getX();	
				
	}	
	
	private void set_ball() {
		ball = new GOval(WIDTH/2 - BALL_RADIUS ,HEIGHT/2-BALL_RADIUS, 2 *(BALL_RADIUS), 2* (BALL_RADIUS));
		ball.setFilled(true);
		add(ball);
	}
	
	private void moveball(){
		ball.move(vx,vy); 
	}
	
	private void collision_check(){
		if(ball.getX()>WIDTH-2*BALL_RADIUS) vx = -vx;
		if(ball.getX()<0)vx = -vx;
		if(ball.getY()<0)vy= -vy;
		if(ball.getY()>HEIGHT) {
			n++;
			set_ball();
		}
	}

	private GObject getCollidingObject(){
		if (getElementAt(ball.getX(),ball.getY())!= null) return getElementAt(ball.getX(),ball.getY());
		else if(getElementAt(ball.getX() + 2*BALL_RADIUS,ball.getY())!= null) return getElementAt(ball.getX() + 2*BALL_RADIUS,ball.getY());
		else if(getElementAt(ball.getX() + 2*BALL_RADIUS,ball.getY() +2*BALL_RADIUS) != null ) return getElementAt(ball.getX() + 2*BALL_RADIUS,ball.getY() +2*BALL_RADIUS);
		else return getElementAt(ball.getX()  ,ball.getY() +2*BALL_RADIUS);
		
	}
	/* Instance variables */
		private GObject gobj;
		private GRect paddle; /* The object being dragged */
		private double lastX,vx,vy,n; /* The last mouse X position */
		private RandomGenerator rgen = RandomGenerator.getInstance();
		private GOval ball;
		
}
