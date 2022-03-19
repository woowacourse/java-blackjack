package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.state.State;
import blackjack.domain.state.running.Init;

import java.util.List;

public abstract class Participant {

    private static final String ERROR_INVALID_NAME = "[ERROR] 유저의 이름은 한 글자 이상이어야 합니다.";

    protected final String name;
    protected final State state;

    public Participant(String name) {
        validateName(name);
        this.name = name;
        this.state = new Init();
    }

    private final void validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(ERROR_INVALID_NAME);
        }
    }

    public final boolean isBust() {
        return state.isBust();
    }

    public final void receiveCard(Deck deck) {
        state.drawCard(deck);
    }

    public final int score() {
        return state.cardSum();
    }

    public final List<Card> getHoldingCards() {
        return state.cards().getAllCards();
    }

    public final String getName() {
        return name;
    }
}
