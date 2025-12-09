import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;  
import java.util.Random;

public class bomb extends Actor {
    public static int speed = -5;  
    private boolean touching = false;  

    public void act() {
        GreenfootImage bombImage = getImage();
        bombImage.scale(90, 100);
        setImage(bombImage);
        
        // Move bomb left
        setLocation(getX() + speed, getY());

        // Update touching state each frame
        touching = isTouching(paddleUser.class);
    }

    public int getYValue() {
        Random randNum = new Random();
        return randNum.nextInt(429);
    }

    public boolean isTouchingUser() {
        return touching;
    }
    
    public int getXPos() {
        return getX();
    }
    
    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

}
