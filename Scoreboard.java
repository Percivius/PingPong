    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Scoreboard extends MyWorld
{
    public static int level() {
        if ((MyWorld.playerScore >= 7 && MyWorld.playerScore < 14) || (MyWorld.botScore >= 7 && MyWorld.botScore < 14)) {
            return 2;          
        }
        else if ((MyWorld.playerScore >= 14 && MyWorld.playerScore < 21) || (MyWorld.botScore >= 14 && MyWorld.botScore < 21)) {
            return 3;          
        }
        else if (MyWorld.playerScore == 21) {
            return 4; // Player wins
        }
        else if (MyWorld.botScore == 21) {
            return 5; // Bot wins
        }
        return 1;
    }
}
