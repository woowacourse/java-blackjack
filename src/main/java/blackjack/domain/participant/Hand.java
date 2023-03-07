package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int FIRST_CARD_INDEX = 0;
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public Hand add(Card card) {
        ArrayList<Card> newCards = new ArrayList<>(this.cards);
        newCards.add(card);
        return new Hand(newCards);
    }

    public Hand add(Card... cards) {
        ArrayList<Card> newCards = new ArrayList<>(this.cards);
        newCards.addAll(List.of(cards));
        return new Hand(newCards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int calculateScore() {
        return Score.from(cards).getValue();
    }

    public Card pickFirstCard() {
        return cards.get(FIRST_CARD_INDEX);
    }
}
