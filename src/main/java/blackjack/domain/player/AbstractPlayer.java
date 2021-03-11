package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;
import java.util.Collections;
import java.util.List;

public abstract class AbstractPlayer implements Player {
    private final Name name;
    protected State state;

    protected AbstractPlayer(String name, Card fistCard, Card secondCard) {
        this.name = new Name(name);
        state = StateFactory.draw(fistCard, secondCard);
    }

    @Override
    public void drawCard(final Card card) {
        state = state.draw(card);
    }

    @Override
    public int getScore() {
        return state.score();
    }

    @Override
    public void drawRandomOneCard() {
        Cards cards = Cards.getInstance();
        drawCard(cards.draw());
    }

    @Override
    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    @Override
    public boolean isBust() {
        return state.isBust();
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(state.cards().getCards());
    }

    @Override
    public void stay() {
        state = state.stay();
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }
}