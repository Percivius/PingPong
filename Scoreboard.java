    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Scoreboard extends MyWorld
{
    public static int playerScore = 0;
    public static int botScore = 0;
    public static void playerScored()
    {
        playerScore++;
    }
    public static void botScored(){
        botScore++;
    }
    public static int gameOver(){
        // 0 = neutral (no winner), 1 = player wins, 2 = bot wins
        if (playerScore >= 21) {
            playerScore = 0;
            return 1;
        }
        else if (botScore >= 21) {
            botScore = 0;
            return 2;
        }
        return 0;
    }

    public static int level() {
        if (playerScore >= 14){

            return 3;
        }
        if (playerScore >= 7){
            return 2;
        }
        else{
            return 1;
        }
    }
}
