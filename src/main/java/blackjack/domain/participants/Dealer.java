package blackjack.domain.participants;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.state.Started;
import blackjack.domain.state.State;
import java.util.Objects;

public class Dealer {

    private static final Score MIN_SCORE_OF_CARDS = new Score(17);

    private State state;

    public Dealer(State state) {
        this.state = state;
    }

    public void prepareBlackjack(Deck deck) {
        state = Started.of(deck.draw(), deck.draw());
    }

    public void drawAdditionalCard(Deck deck) {
        while (state.calculateTotalScore().compareTo(MIN_SCORE_OF_CARDS) < 0) {
            state = state.draw(deck.draw());
        }
        state = state.stay();
    }

    public Score calculateMaxScore() {
        return state.calculateTotalScore();
    }

    public int calculateProfit(Players players) {
        return -1 * players.calculateTotalProfit(state);
    }

    public Cards getCards() {
        return state.cards();
    }

    public State getState() {
        return state;
    }

    public int getCardSize() {
        return state.cards().size();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Dealer dealer = (Dealer) object;
        return Objects.equals(getCards(), dealer.getCards());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCards());
    }
}
