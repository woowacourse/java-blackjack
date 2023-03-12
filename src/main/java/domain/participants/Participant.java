package domain.participants;

import domain.deck.Card;
import domain.deck.Cards;

import java.util.List;

public class Participant {
    private static final int BLACK_JACK = 21;

    private final Name name;
    private final Cards cards;

    public Participant(Name name) {
        this.name = name;
        this.cards = new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getCardsSum() {
        return cards.getSum();
    }

    public boolean isBust() {
        return getCardsSum() > BLACK_JACK;
    }

    public boolean isBlackJack() {
        return (getCardsSum() == BLACK_JACK) && cards.getCards().size() == 2;
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
