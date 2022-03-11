package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class CardMachine {

    public static final int INIT_CARD_COUNT = 2;

    private final CardDeck cardDeck = new CardDeck();

    public List<Card> pickInitCards() {
        List<Card> initCards = new ArrayList<>();
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            initCards.add(cardDeck.pick());
        }
        return initCards;
    }

    public Card pickCard() {
        return cardDeck.pick();
    }
}
