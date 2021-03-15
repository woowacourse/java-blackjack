package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.state.Hit;
import blackjack.domain.state.State;
import blackjack.domain.state.Stay;
import java.util.Collections;

public class Dealer implements Participant {
    public static final String DEALER_NAME = "딜러";

    private static final Score MINIMUM_SCORE_OF_TAKING_CARD = Score.of(16);
    private final Cards cards;
    private State state;

    public Dealer() {
        this(new Cards(Collections.emptyList()));
    }

    public Dealer(Cards cards) {
        this.cards = cards;
        this.state = new Hit(cards);
    }

    @Override
    public boolean isAbleToTake() {
        final Score score = cards.sumCards();
        if (score.isEqualAndLessThan(MINIMUM_SCORE_OF_TAKING_CARD)) {
            return true;
        }
        state = new Stay(cards);
        return false;
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    @Override
    public void takeCard(Card card) {
        state = state.takeCard(card);
    }

    @Override
    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    @Override
    public boolean isBurst() {
        return state.isBurst();
    }

    @Override
    public Score finalScore() {
        return state.calculateScore();
    }

    @Override
    public Cards getCards() {
        return state.getCards();
    }

    @Override
    public int sizeOfCards() {
        return state.size();
    }

}
