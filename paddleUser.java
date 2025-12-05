import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class paddleUser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class paddleUser extends Actor
{
    /**
     * Act - do whatever the paddleUser wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public static int pUserChange = 10;
    public void act()
    {
        paddleUser actor = new paddleUser();
        GreenfootImage userPaddleImage = getImage();
        userPaddleImage.scale(20,100);
         if (Greenfoot.isKeyDown("down")) {
              setLocation(getX(), getY() + pUserChange);
            }
         if (Greenfoot.isKeyDown("up")) {
              setLocation(getX(), getY() - pUserChange);
            }
    }
}
