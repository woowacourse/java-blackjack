package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class CardMachine {

    private static final int INIT_CARD_COUNT = 2;

    private final Cards cards = new Cards();

    public CardMachine() {
        cards.generate();
    }

    public List<Card> pickInitCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            cards.add(this.cards.pick());
        }
        return cards;
    }

    public Card pickCard() {
        return cards.pick();
    }
}
