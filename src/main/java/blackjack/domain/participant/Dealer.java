package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Dealer {

    private static final String DEALER_NAME = "딜러";
    private static final int DRAWABLE_LIMIT_VALUE = 16;
    private static final int PLAYING_STANDARD = 21;

    private final Name name;
    private final Cards cards;

    public Dealer(List<Card> cards) {
        this.name = new Name(DEALER_NAME);
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

    public GameResult decideResult(int playerScore) {
        if (playerScore > PLAYING_STANDARD) {
            return GameResult.WIN;
        }

        if (getTotalScore() > PLAYING_STANDARD) {
            return GameResult.LOSE;
        }

        return GameResult.of(getTotalScore() - playerScore);
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return cards.getValue();
    }
}
