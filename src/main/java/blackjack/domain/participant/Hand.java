package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;

public class Hand {
    public static final int FIRST_CARD_INDEX = 0;
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
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
