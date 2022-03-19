package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import blackjack.domain.card.property.CardNumber;
import blackjack.domain.card.property.CardShape;

public class CardPack {
    private static final List<Card> cards = createCards();

    private final List<Card> cardPack = new LinkedList<>();

    public CardPack() {
        initializeCards();
    }

    private static List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        for (CardShape cardShape : CardShape.values()) {
            addCards(cards, cardShape);
        }
        return cards;
    }

    private static void addCards(List<Card> cards, CardShape cardShape) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardShape, cardNumber));
        }
    }

    private void initializeCards() {
        Collections.shuffle(cards);
        cardPack.addAll(cards);
    }

    public Card pickOne() {
        if (cardPack.isEmpty()) {
            initializeCards();
        }

        return cardPack.remove(0);
    }
}
