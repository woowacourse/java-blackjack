import blackjack.Blackjack;
import blackjack.Casino;

public class Application {
    public static void main(String[] args) {
        Casino casino = new Casino(new Blackjack());
        casino.playBlackJack();
    }
}
