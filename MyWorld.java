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
    private mpbutton multiButton;
    private spbutton singleButton;
    private trophy prize;

    // Private variables here
    private int xChange = -5;
    private int yChange = 0;
    private boolean isRunning = false;
    private boolean playerScored = false;
    private boolean botScored = false;
    public static boolean pressedSinglePlayer = false;
    public static boolean pressedMultiPlayer = false;
    private boolean bombsCreated = false;
    private boolean speedChanged = false;
    private boolean bombOneExploding = false;
    private boolean bombTwoExploding = false;
    private int bombOneExplosionCounter = 0;
    private int bombTwoExplosionCounter = 0;

    // Image vars so I don't have to keep typing in the file name T_T
    private final String bombImage = "Adobe Express - file (2).png";
    private final String explodeImage = "kaboom.png";

    // Private paddle-related variables
    int xPaddleUser; 
    int yMinPaddleUserUpper;
    int yMinPaddleUserLower;
    private int strikeCount = 0;
    private bomb bombOne;
    private bomb bombTwo;

    int xPaddleBot; 
    int yMinPaddleBotUpper;
    int yMinPaddleBotLower;

    // Scoreboard-related variables
    public static int playerScore = 0; 
    public static int botScore = 0;

    public MyWorld()
    {    
        super(900, 580, 1);
        prepare(); // Sets up background, etc.
        
        // Initial setup for the start screen

        multiButton = new mpbutton();
        addObject(multiButton, 673, 290);
        singleButton = new spbutton();
        addObject(singleButton, 222, 290);
        prize = new trophy();
        
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
        playerScore = 0;
        botScore = 0;
        
        isRunning = true;
        Greenfoot.playSound("win.mp3");
        Greenfoot.playSound("backtrack.mp3");

        // Initialize and add objects (moved from the constructor)
        gameBall = new Ball();
        addObject(gameBall, 450, 290);
        uPad = new paddleUser();
        addObject(uPad, 70, 290);
        bPad = new paddleBot();
        addObject(bPad, 810, 290);
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
            removeObject(singleButton);
            removeObject(multiButton);
            startGame(); // Call the method to set up and start the game
        } else if (!isRunning && Greenfoot.mouseClicked(multiButton)) {
            pressedMultiPlayer = true;
            removeObject(singleButton);
            removeObject(multiButton);
            startGame();
        }
        
        if (playerScored || botScored) {
            resetGame(); // Resets game when someone scores
            xChange = -5;
        }
        if (!isRunning) {
            return; // Exit act() immediately if game isn't running
        } 

        if (Scoreboard.level() == 2) {
            if (!bombsCreated) {
                bombOne = new bomb();
                bombTwo = new bomb();
                bombOne.setImage(bombImage);
                bombTwo.setImage(bombImage);
                addObject(bombOne, 800, bombOne.getYValue());
                addObject(bombTwo, 800, bombTwo.getYValue());
                bombsCreated = true;
            }
            
            // Bomb One explosion stuff
            if (bombOneExploding) {
                bombOneExplosionCounter--;
                if (bombOneExplosionCounter <= 0) {
                    // End explosion: reset image and respawn position
                    bombOne.setImage(bombImage);
                    bombOne.setLocation(800, bombOne.getYValue());
                    bombOneExploding = false;
                }
            } else {
                // Only check interactions when not exploding
                if (bombOne.isTouchingUser() || (bombOne.getX() <= 0 || bombOne.getX() >= getWidth() ||
    bombOne.getY() <= 0 || bombOne.getY() >= getHeight())) {
                    if (bombTwo.isTouchingUser()) { strikeCount++; }
                    bombOne.setImage(explodeImage);
                    bombOneExploding = true;
                    bombOneExplosionCounter = 30; // ~1 second at 60 fps
                } else {
                    // If not exploding
                    int bx = bombOne.getX();
                    if (bx + 28 > 900 || bx + 28 <= 0) {
                        bombOne.setImage(bombImage); // ensure original on respawn
                        bombOne.setLocation(800, bombOne.getYValue());
                    }
                }
            }

            // Bomb Two explosion stuff
            if (bombTwoExploding) {
                bombTwoExplosionCounter--;
                if (bombTwoExplosionCounter <= 0) {
                    bombTwo.setImage(bombImage);
                    bombTwo.setLocation(800, bombTwo.getYValue());
                    bombTwoExploding = false;
                }
            } else {
                if (bombTwo.isTouchingUser() || (bombTwo.getX() <= 0 || bombTwo.getX() >= getWidth() ||
    bombTwo.getY() <= 0 || bombTwo.getY() >= getHeight())) {
                    if (bombTwo.isTouchingUser()) { strikeCount++; }
                    bombTwo.setImage(explodeImage);
                    bombTwoExploding = true;
                    bombTwoExplosionCounter = 30;
                } else {
                    int bx2 = bombTwo.getX();
                    if (bx2 + 28 > 900 || bx2 + 28 <= 0) {
                        bombTwo.setImage(bombImage);
                        bombTwo.setLocation(800, bombTwo.getYValue());
                    }
                }
            }
        }
        
        // Pretty straightforward for level 3; just upping the speed for all three objects
        if (Scoreboard.level() == 3) {
            if (!speedChanged) {
                xChange += 2;
                yChange += 2;
                bombOne.setSpeed(-7);
                bombTwo.setSpeed(-7);
                speedChanged = true;
            }
            
            // All copy paste from level two because it still applies (i should probably make this a method)
            if (bombOneExploding) {
                bombOneExplosionCounter--;
                if (bombOneExplosionCounter <= 0) {
                    // End explosion: reset image and respawn position
                    bombOne.setImage(bombImage);
                    bombOne.setLocation(800, bombOne.getYValue());
                    bombOneExploding = false;
                }
            } else {
                // Only check interactions when not exploding
                if (bombOne.isTouchingUser() || (bombOne.getX() <= 0 || bombOne.getX() >= getWidth() ||
    bombOne.getY() <= 0 || bombOne.getY() >= getHeight())) {
                    if (bombTwo.isTouchingUser()) { strikeCount++; }
                    bombOne.setImage(explodeImage);
                    bombOneExploding = true;
                    bombOneExplosionCounter = 30; // ~1 second at 60 fps
                } else {
                    // If not exploding
                    int bx = bombOne.getX();
                    if (bx + 28 > 900 || bx + 28 <= 0) {
                        bombOne.setImage(bombImage); // ensure original on respawn
                        bombOne.setLocation(800, bombOne.getYValue());
                    }
                }
            }

            // Bomb Two explosion stuff
            if (bombTwoExploding) {
                bombTwoExplosionCounter--;
                if (bombTwoExplosionCounter <= 0) {
                    bombTwo.setImage(bombImage);
                    bombTwo.setLocation(800, bombTwo.getYValue());
                    bombTwoExploding = false;
                }
            } else {
                if (bombTwo.isTouchingUser() || (bombTwo.getX() <= 0 || bombTwo.getX() >= getWidth() ||
    bombTwo.getY() <= 0 || bombTwo.getY() >= getHeight())) {
                    if (bombTwo.isTouchingUser()) { strikeCount++; }
                    bombTwo.setImage(explodeImage);
                    bombTwoExploding = true;
                    bombTwoExplosionCounter = 30;
                } else {
                    int bx2 = bombTwo.getX();
                    if (bx2 + 28 > 900 || bx2 + 28 <= 0) {
                        bombTwo.setImage(bombImage);
                        bombTwo.setLocation(800, bombTwo.getYValue());
                    }
                }
            }
        }
        
        if (Scoreboard.level() == 4 || Scoreboard.level() == 5) {
            isRunning = false;
            removeObjects(getObjects(null));
            System.out.println("Game terminated. Ignore all error messages.");
            
            // Bot wins
            if (Scoreboard.level() == 5) {
                showText("You lost. Try again next time!", 450, 225);
            }
            
            if (Scoreboard.level() == 4) {
                showText("You won the tournament! Congratulations!", 450, 225);
                addObject(prize, 450, 290);
            }
        }

        // Converting scores to strings and displaying
        // Converting scores to strings and displaying (can be updated here if scores change)
        String playerScoreString = String.valueOf(Scoreboard.playerScore);
        String botScoreString = String.valueOf(Scoreboard.botScore);
        showText(playerScoreString, 300, 50);
        showText(botScoreString, 600, 50);
        showText("LEVEL: " + String.valueOf(Scoreboard.level()), 450, 50);

        // Note that ball movement stays the same across single and multiplayer mode. No need to transfer over to separate classes.
        xPaddleUser = uPad.getX() + 10; 
        yMinPaddleUserUpper = uPad.getY() - 50;
        yMinPaddleUserLower = uPad.getY() + 50;
    
        xPaddleBot = bPad.getX() - 10; // Get bot's X position (adjust offset as needed)
        yMinPaddleBotUpper = bPad.getY() - 50;
        yMinPaddleBotLower = bPad.getY() + 50;
        
        // Check collision with User Paddle (left side)
        // Both X position MUST match AND Y position MUST be in range
        if ((gameBall.getX() - 28 <= xPaddleUser) && (gameBall.getY() >= yMinPaddleUserUpper && gameBall.getY() <= yMinPaddleUserLower)) {
            xChange = 7; // Change direction to move right
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
            xChange = -7; // Change direction to move left
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
            showText("LEVEL: " + String.valueOf(Scoreboard.level()),450,50);
            playerScored = true;
            isRunning = false;
        }
        if (gameBall.getX() - 28 < 0) { // Check both boundaries
            botScore++;
            showText(String.valueOf(botScore), 600, 50);
            showText("LEVEL: " + String.valueOf(Scoreboard.level()),450,50);
            botScored = true;
            isRunning = false; // Game over
        }
        if (gameBall.getY() - 28 < 0) {
            yChange = 3;
        }
        if (gameBall.getY() + 28 > 600) {
            yChange = -3;
        }
        
        // Strike logic
        if (strikeCount >= 3) {
            strikeCount = 0;
            botScore++;
        }
        
        // Move the ball horizontally
        gameBall.setLocation(gameBall.getX() + xChange, gameBall.getY() + yChange);
    }
    
    // Accessor methods
    public int getUserX() {
        return xPaddleUser;
    }
    
    public int getBotX() {
        return xPaddleBot;   
    }
    
    public int getUserUpperY() {
        return yMinPaddleUserUpper;
    }
    
    public int getUserYLower() {
        return yMinPaddleUserLower;
    }
    
    public int getBotYUpper() { return yMinPaddleBotUpper; }
    public int getBotYLower() { return yMinPaddleBotLower; }
}
