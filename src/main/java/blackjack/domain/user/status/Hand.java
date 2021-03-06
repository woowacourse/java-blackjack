package blackjack.domain.user.status;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int FIRST_INDEX = 0;

    private final Cards cards;

    public Hand(Cards cards){
        this.cards = cards;
    }

    public static Hand createEmptyHand() {
        return new Hand(new Cards(Collections.emptyList()));
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public Card getFirstCard() {
        return cards.getCards().get(FIRST_INDEX);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }
}
