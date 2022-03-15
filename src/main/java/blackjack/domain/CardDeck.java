package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    private static final int INIT_CARD_COUNT = 2;

    private final List<Card> cardDeck = new ArrayList<>();

    public CardDeck() {
        cardDeck.addAll(Card.getDeck());
        Collections.shuffle(cardDeck);
    }

    public List<Card> dealInit() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            cards.add(drawCard());
        }
        return cards;
    }

    public Card drawCard() {
        return cardDeck.remove(0);
    }
}
