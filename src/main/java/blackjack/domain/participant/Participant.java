package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Participant {
    protected Name name;
    protected Cards cards = new Cards();

    public Participant(String name) {
        this.name = new Name(name);
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
}
