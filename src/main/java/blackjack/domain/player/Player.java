package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.*;

public abstract class Player {

    private final Name name;
    protected final Cards cards;
    protected State state;

    protected Player(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
        this.state = StateFactory.drawFirstCards(cards);
    }

    public abstract void addCard(Card card);

    public abstract boolean canDraw();

    public boolean isBust() {
        return cards.isBust();
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public String getName() {
        return name.getName();
    }

    public Cards getCards() {
        return cards;
    }

    public boolean isBlackjack() {
        return calculateScore() == 21 && cards.size() == 2;
    }

    public boolean isBlackJack() {
        System.out.println(Blackjack.class.isInstance(state));
        return Blackjack.class.isInstance(state);
    }

    public boolean isbust() {
        return Bust.class.isInstance(state);
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public void stay() {
        state = state.stay();
    }

    public void draw(Card card) {
        state = state.draw(card);
    }
}
