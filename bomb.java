import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class bomb extends Actor
{
    /**
     * Act - do whatever the bomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int speed = -5; 
    private boolean imageScaled = false;

    public void act()
    {
        if (!imageScaled)
        {
            GreenfootImage bombImage = getImage();
            bombImage.scale(90, 100);
            setImage(bombImage);
            imageScaled = true;
        }

        setLocation(getX()-5, getY());
        if (isTouching(paddleUser.class)) {
            setImage("kaboom.png");
        }
    }
}
