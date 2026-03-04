import java.util.List;

public class GameManager {
    private Deck deck = new Deck();
    private ScoreCalculator scoreCalculator = new ScoreCalculator();

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

    public void judgeBust(int score, Player currentPlayer) {
        if (scoreCalculator.isBust(score)) {
            currentPlayer.setBust();
        }
    }

    public int calculateScore(List<Card> hand) {
        return scoreCalculator.calculateScore(hand);
    }

    public void drawCard(Player player) {
        player.addCard(deck.draw());
    }
}
