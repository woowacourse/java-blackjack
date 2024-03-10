import blackjack.domain.Blackjack;

public class Application {
    public static void main(final String[] args) {
        final Casino casino = new Casino(new Blackjack());
        casino.playBlackJack();
    }
}
