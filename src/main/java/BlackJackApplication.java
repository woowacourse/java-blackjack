import controller.BlackJackController;
import domain.card.RandomCardShuffler;

public class BlackJackApplication {
    public static void main(String[] args) {
        new BlackJackController(new RandomCardShuffler()).run();
    }
}
