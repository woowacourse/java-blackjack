package domain;

public class Player {

    private final Cards cards;
    private final String name;

    public Player(Cards cards, String name) {
        this.cards = cards;
        this.name = name;
    }

    public static Player of(Cards cards, String name) {
        return new Player(cards, name);
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public boolean isBust() {
        return cards.isBust(cards.calculateScore());
    }

    public int getScoreOrZeroIfBust() {
        return cards.getScoreOrZeroIfBust();
    }

    public boolean isInitialHand() {
        return cards.size() == Policy.FIRST_DRAW_SIZE;
    }

    public GameResult compareResult(Dealer dealer) {
        int playerScore = getScoreOrZeroIfBust();
        int dealerScore = dealer.getScoreOrZeroIfBust();

        if (playerScore > dealerScore) {
            return GameResult.WIN;
        }
        if (playerScore < dealerScore) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
