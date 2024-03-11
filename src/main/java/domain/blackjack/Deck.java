package domain.blackjack;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        cards = init();
    }

    private List<Card> init() {
        List<Card> initialCards = Card.getCache();
        Collections.shuffle(initialCards);
        return initialCards;
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("드로우할 카드가 남아있지 않습니다.");
        }
        return cards.remove(0);
    }

    public int getCardCount() {
        return cards.size();
    }
}
