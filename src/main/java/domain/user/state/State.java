package domain.user.state;

import domain.card.Card;
import domain.game.Winning;
import domain.user.Cards;
import java.util.List;

public abstract class State {

    protected Cards cards;

    protected State() {
        this.cards = new Cards();
    }

    protected State(Cards cards) {
        this.cards = cards;
    }

    public abstract State draw(Card card);

    public abstract boolean isDrawable();

    public abstract State stay();

    public abstract Winning match(State dealer);

    public abstract boolean isBlackJack();

    public abstract boolean isBust();

    public List<Card> getCards() {
        return cards.getCards();
    }

    public int getScore() {
        return cards.getSumOfScores();
    }
}
