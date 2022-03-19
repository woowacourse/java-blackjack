package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.state.State;
import blackjack.domain.state.running.Init;

import java.util.List;

public abstract class Participant {

    private static final String ERROR_INVALID_NAME = "[ERROR] 유저의 이름은 한 글자 이상이어야 합니다.";

    protected final String name;
    protected State state;

    public Participant(String name) {
        validateName(name);
        this.name = name;
        this.state = new Init();
    }

    private void validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(ERROR_INVALID_NAME);
        }
    }

    public final boolean isBust() {
        return state.holdingCards().isBust();
    }

    public final void drawCard(Deck deck) {
        state = state.drawCard(deck);
    }

    public final void changeToStand() {
        state = state.stand();
    }

    public final int score() {
        return state.cardScore();
    }

    public final List<Card> getHoldingCards() {
        return state.holdingCards().getAllCards();
    }

    public final String getName() {
        return name;
    }
}
