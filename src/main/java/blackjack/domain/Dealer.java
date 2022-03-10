package blackjack.domain;


import java.util.List;

public class Dealer {

    private static final int DRAWABLE_LIMIT_VALUE = 16;
    private static final int PLAYING_STANDARD = 21;

    private final Cards cards;

    public Dealer(List<Card> cards) {
        this.cards = new Cards(cards);
    }

    public int getTotalScore() {
        return cards.calculateTotalScore();
    }

    public boolean isDrawable() {
        return cards.calculateTotalScore() <= DRAWABLE_LIMIT_VALUE;
    }

    public void combine(Card card) {
        cards.combine(card);
    }

    public GameResult createResult(int playerScore) {
        if (playerScore > PLAYING_STANDARD) {
            return GameResult.WIN;
        }

        if (getTotalScore() > PLAYING_STANDARD) {
            return GameResult.LOSE;
        }

        return GameResult.of(getTotalScore() - playerScore);
    }

    public List<Card> getCards() {
        return cards.getValue();
    }
}
