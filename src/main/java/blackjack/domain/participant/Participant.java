package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.status.StartRound;
import blackjack.domain.status.State;

import java.util.List;

public abstract class Participant {
    private State state;

    public void startRound(final Card first, final Card second) {
        state = StartRound.draw(first, second);
    }

    public void addCard(Card card) {
        state = state.draw(card);
    }

    public int cardResult() {
        return state.getCards()
                    .calculateResult();
    }

    public boolean isBlackJack() {
        return state.getCards()
                    .isBlackJack();
    }

    public boolean isBust() {
        return state.getCards()
                    .isBust();
    }

    public List<Card> getCards() {
        return state.getCards()
                    .toList();
    }

    public List<Card> getOpenCard() {
        return state.getCards()
                    .subList(0, numberOfOpenCard());
    }

    public abstract int numberOfOpenCard();

    public abstract String getName();
}


