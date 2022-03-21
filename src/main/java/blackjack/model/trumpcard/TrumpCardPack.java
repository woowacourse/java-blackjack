package blackjack.model.trumpcard;

import java.util.Collections;
import java.util.LinkedList;

public final class TrumpCardPack {
    private static final String ERROR_EMPTY = "[ERROR] 더 이상 카드를 꺼낼 수 없습니다.";

    private final LinkedList<TrumpCard> values;

    public TrumpCardPack() {
        this.values = createCards();
    }

    private LinkedList<TrumpCard> createCards() {
        LinkedList<TrumpCard> cards = new LinkedList<>();
        stackCards(cards);
        Collections.shuffle(cards);
        return cards;
    }

    private void stackCards(LinkedList<TrumpCard> cards) {
        for (TrumpSuit suit : TrumpSuit.values()) {
            stackCardsOfSuit(cards, suit);
        }
    }

    private void stackCardsOfSuit(LinkedList<TrumpCard> cards, TrumpSuit suit) {
        for (TrumpDenomination denomination : TrumpDenomination.values()) {
            cards.add(new TrumpCard(denomination, suit));
        }
    }

    public TrumpCard draw() {
        checkEmpty();
        return this.values.pop();
    }

    private void checkEmpty() {
        if (this.values.isEmpty()) {
            throw new IllegalArgumentException(ERROR_EMPTY);
        }
    }
}
