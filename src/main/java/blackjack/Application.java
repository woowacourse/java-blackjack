package blackjack;

import blackjack.game.RunningGame;

public class Application {
    public static void main(String[] args) {
        RunningGame runningGame = new RunningGame();
        runningGame.run();
    }
}
