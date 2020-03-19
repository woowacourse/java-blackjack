package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;
import java.util.Objects;

public abstract class Participant {
    protected final Name name;
    protected final Cards cards = new Cards();

    public Participant(Name name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    public abstract boolean canGetMoreCard();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<String> showCards() {
        return cards.cardsInfo();
    }

    public int computeScore() {
        return cards.computeScore();
    }

    public String name() {
        return name.getName();
    }

    public Cards getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }
}
