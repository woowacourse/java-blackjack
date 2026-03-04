import java.util.List;

public class GameManager {
    private Deck deck = new Deck();

    public GameManager() {
        deck.init();
        deck.shuffle();
    }

    public void dealCards(List<Player> records, Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            for (Player player : records) {
                player.addCard(deck.draw());
            }
            dealer.addCard(deck.draw());
        }
    }
}
