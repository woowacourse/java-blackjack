import blackjack.domain.card.RandomGenerator;
import blackjack.game.ConsoleGame;

public class Application {
    public static void main(String[] args) {
        RandomGenerator randomGenerator = new RandomGenerator();

        ConsoleGame consoleGame = new ConsoleGame(randomGenerator);
        consoleGame.run();
    }
}
