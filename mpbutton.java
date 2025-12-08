import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class mpbutton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class mpbutton extends Actor
{
    /**
     * Act - do whatever the mpbutton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        mpbutton actor=new mpbutton();
        GreenfootImage mpImage=getImage();
        mpImage.scale(450,300);
         if(Greenfoot.mouseClicked(this)){
            MyWorld.mptrue = true;
        }
    }
}
