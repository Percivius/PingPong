import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
/**
 * Write a description of class spmovement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class spMovement extends MyWorld
{
    /**
     * Act - do whatever the spmovement wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private Ball gameBall;
    private paddleUser uPad;
    private paddleBot bPad;
    private mpbutton multiButton;
    private spbutton singleButton;
    private trophy prize;
    private int xChange = -5;
    private int yChange = 0;
    private boolean isRunning = false;
    private int playerScore = Scoreboard.playerScore; 
    private int botScore = Scoreboard.botScore;
    public void act()
    {
           // Converting scores to strings and displaying
        // Converting scores to strings and displaying (can be updated here if scores change)
        String playerScoreString = String.valueOf(Scoreboard.playerScore);
        String botScoreString = String.valueOf(Scoreboard.botScore);
        showText(playerScoreString, 300, 50);
        showText(botScoreString, 600, 50);
        showText("LEVEL: "+String.valueOf(Scoreboard.level()),450,50);

        // Range of constantly-changing values (get current locations in each act cycle)
        int xPaddleUser = uPad.getX() + 10; 
        int yMinPaddleUserUpper = uPad.getY() - 50;
        int yMinPaddleUserLower = uPad.getY() + 50;
    
        int xPaddleBot = bPad.getX() - 10; // Get bot's X position (adjust offset as needed)
        int yMinPaddleBotUpper = bPad.getY() - 50;
        int yMinPaddleBotLower = bPad.getY() + 50;
        
        // Check collision with User Paddle (left side)
        // Both X position MUST match AND Y position MUST be in range
        if ((gameBall.getX() - 28 <= xPaddleUser) && (gameBall.getY() >= yMinPaddleUserUpper && gameBall.getY() <= yMinPaddleUserLower)) {
            xChange = 5; // Change direction to move right
            if (Greenfoot.isKeyDown("down")) {
                yChange = -2;
            }
            if (Greenfoot.isKeyDown("up")) {
                yChange = 2;
            }
        }
    
        // Check collision with Bot Paddle (right side)
        // Both X position MUST match AND Y position MUST be in range
        if ((gameBall.getX() + 28 >= xPaddleBot) && (gameBall.getY() >= yMinPaddleBotUpper && gameBall.getY() <= yMinPaddleBotLower)) {
            xChange = -5; // Change direction to move left
            if (Greenfoot.isKeyDown("down")) {
                yChange = -2;
            }
            if (Greenfoot.isKeyDown("up")) {
                yChange = 2;
            }
        }
        
        // Check if the ball goes off the side walls
        if (gameBall.getX() + 28 > 900) {
            playerScore++;
            showText(String.valueOf(playerScore), 300, 50);
            isRunning = false;
        }
        if (gameBall.getX() - 28 < 0) { // Check both boundaries
            botScore++;
            showText(String.valueOf(botScore), 600, 50);
            isRunning = false; // Game over
        }
        if (gameBall.getY() - 28 < 0) {
            yChange = 2;
        }
        if (gameBall.getY() + 28 > 600) {
            yChange = -2;
        }
        if (Scoreboard.gameOver()==1){
            showText("You won the Tournament!",450,50);
            addObject(prize,450,290);
        }
        // Move the ball horizontally
        gameBall.setLocation(gameBall.getX() + xChange, gameBall.getY() + yChange);
    }
}
