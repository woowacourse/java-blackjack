package blackjack.domain;

import java.util.List;
import java.util.Objects;

public abstract class Participant {
    protected final Name name;
    protected final Cards cards;

    protected Participant(String name, List<Card> cards) {
        this.name = new Name(name);
        this.cards = new Cards(cards);
    }

    public void addCard(Card card) {
        this.cards.addCard(card);
    }

    public Score getScore() {
        return cards.getScore();
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public boolean isSameName(Name name) {
        return Objects.equals(this.name, name);
    }

    public abstract boolean isPlayer();

    public abstract boolean isDealer();

    public abstract List<Card> getStartCards();

    public abstract boolean canDrawCard();
}
