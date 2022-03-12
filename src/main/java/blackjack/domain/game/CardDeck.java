package blackjack.domain.game;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardDeck {

    public static final int INIT_CARD_COUNT = 2;

    private final Stack<Card> cardDeck = new Stack<>();

    public CardDeck() {
        cardDeck.addAll(Card.getTotalCard());
        Collections.shuffle(cardDeck);
    }

    public List<Card> pickInit() {
        List<Card> initCards = new ArrayList<>();
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            initCards.add(pick());
        }
        return initCards;
    }

    public Card pick() {
        return cardDeck.pop();
    }
}
