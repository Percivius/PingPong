import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class MyWorld here.
 * 
 * @author Shubhay Shah
 * @version 1.0.0
 */

public class MyWorld extends World
{
    // Private objects here
    private Ball gameBall;
    private paddleUser uPad;
    private paddleBot bPad;
<<<<<<< HEAD
    private start startButton;
    private mpbutton multiButton;
    private spbutton singleButton;
    private trophy prize;
    private bomb fbomb;

    // Private variables here
    private int xChange = -5;
    private int yChange = 0;
    private boolean isRunning = false;
    private boolean playerScored = false;
    private boolean botScored = false;
    public static boolean pressedSinglePlayer = false;
    public static boolean pressedMultiPlayer = false;

    // Scoreboard-related variables
=======
    private mpbutton multiButton;
    private spbutton singleButton;
    private trophy prize;
    private int xChange = -5;
    private int yChange = 0;
    private boolean isRunning = false;
    public static boolean mptrue = false;
    public static boolean sptrue = false;
>>>>>>> 3c9984ebc9178ef05ac2662ea184e49cddc6df9c
    private int playerScore = Scoreboard.playerScore; 
    private int botScore = Scoreboard.botScore;

    public MyWorld()
    {    
        super(900, 580, 1);
        prepare(); // Sets up background, etc.
        
        
        // Initial setup for the start screen
<<<<<<< HEAD
        multiButton = new mpbutton();
        addObject(multiButton, 673, 290);
        singleButton = new spbutton();
        addObject(singleButton, 222, 290);
        prize = new trophy();
=======
        
>>>>>>> 3c9984ebc9178ef05ac2662ea184e49cddc6df9c
    }
    
    /**
     * Prepare the world for the start of the program.
     */
    private void prepare()
    {
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
    }
    
    private void startGame() {
        isRunning = true;
<<<<<<< HEAD
        Greenfoot.playSound("win.mp3");
        removeObject(startButton); // Remove the start button/text
        Greenfoot.playSound("backtrack.mp3");
=======

>>>>>>> 3c9984ebc9178ef05ac2662ea184e49cddc6df9c
        // Initialize and add objects (moved from the constructor)
        gameBall = new Ball();
        addObject(gameBall, 450, 290);
        uPad = new paddleUser();
        addObject(uPad, 70, 290);
        bPad = new paddleBot();
        addObject(bPad, 810, 290);
<<<<<<< HEAD
    }
    
    private void spawnBombs() {
=======
        multiButton=new mpbutton();
        addObject(multiButton,673,290);
        singleButton=new spbutton();
        addObject(singleButton,222,290);
        prize=new trophy();

>>>>>>> 3c9984ebc9178ef05ac2662ea184e49cddc6df9c
        // Randomly spawning in bombs
        fbomb = new bomb();
        int x = (int)(Math.random() * 581);
        addObject(fbomb, 800, x);
    }
    
    private void resetGame() {
        gameBall.setLocation(450, 290);
        uPad.setLocation(70, 290);
        bPad.setLocation(810, 290);
        
        playerScored = false;
        botScored = false;
        isRunning = true;
    }

    public void act() {
        if (!isRunning && Greenfoot.mouseClicked(singleButton)) {
            pressedSinglePlayer = true;
            startGame(); // Call the method to set up and start the game
        } else if (!isRunning && Greenfoot.mouseClicked(multiButton)) {
            pressedMultiPlayer = true;
            startGame();
        }
        if (Scoreboard.gameOver() == 1){
            showText("You won the tournament! Congratulations!", 450, 50);
            addObject(prize,450,290);
        }
        
        if (playerScored || botScored) {
            resetGame(); // Resets game when someone scores
            xChange = -5;
        }
        if (!isRunning) {
            return; // Exit act() immediately if game isn't running
        } 
<<<<<<< HEAD

        if (Scoreboard.level() == 2) {
            spawnBombs();
        }
        if (fbomb.getX() + 28 > 900) {
            removeObject(fbomb);
            spawnBombs();
        }
        if (fbomb.getX() - 28 < 0) { // Check both boundaries
            removeObject(fbomb);
            spawnBombs();
=======
        if(mptrue == true){
            //call multi mode
        }
        else if(sptrue == true){
            //call single
>>>>>>> 3c9984ebc9178ef05ac2662ea184e49cddc6df9c
        }
        // Converting scores to strings and displaying
        // Converting scores to strings and displaying (can be updated here if scores change)
        String playerScoreString = String.valueOf(Scoreboard.playerScore);
        String botScoreString = String.valueOf(Scoreboard.botScore);
        showText(playerScoreString, 300, 50);
        showText(botScoreString, 600, 50);
        showText("LEVEL: "+String.valueOf(Scoreboard.level()),450,50);

        // Note that ball movement stays the same across single and multiplayer mode. No need to transfer over to separate classes.
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
                yChange = 2;
            }
            if (Greenfoot.isKeyDown("up")) {
                yChange = -2;
            }
        }
        
        // Check if the ball goes off the side walls
        if (gameBall.getX() + 28 > 900) {
            playerScore++;
            showText(String.valueOf(playerScore), 300, 50);
            playerScored = true;
            isRunning = false;
        }
        if (gameBall.getX() - 28 < 0) { // Check both boundaries
            botScore++;
            showText(String.valueOf(botScore), 600, 50);
            botScored = true;
            isRunning = false; // Game over
        }
        if (gameBall.getY() - 28 < 0) {
            yChange = 2;
        }
        if (gameBall.getY() + 28 > 600) {
            yChange = -2;
        }
<<<<<<< HEAD
        
        if ((fbomb.getX() - 28 <= xPaddleUser) && (fbomb.getY() >= yMinPaddleUserUpper && fbomb.getY() <= yMinPaddleUserLower)) {
            Scoreboard.botScore++; 
            removeObject(fbomb);
            spawnBombs();
        }
    
        // Check collision with Bot Paddle (right side)
        // Both X position MUST match AND Y position MUST be in range
        if ((fbomb.getX() + 28 >= xPaddleBot) && (fbomb.getY() >= yMinPaddleBotUpper && fbomb.getY() <= yMinPaddleBotLower)) {
            // explosion image replaces bomb
            Scoreboard.playerScore++;
            removeObject(fbomb);
            spawnBombs();
        }
        
=======
        if (Scoreboard.gameOver()==1){
            showText("You won the Tournament!!!!",450,50);
            addObject(prize,450,290);
        }
>>>>>>> 3c9984ebc9178ef05ac2662ea184e49cddc6df9c
        // Move the ball horizontally
        gameBall.setLocation(gameBall.getX() + xChange, gameBall.getY() + yChange);
    }
 
    // Remove or comment out the default started/stopped overrides which interfere with manual control
    // @Override
    // public void started() {
    //     isRunning = true; 
    // }
    
    // @Override
    // public void stopped() {
    //     isRunning = false;
    // }
    
}
