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
        for (TrumpSymbol symbol : TrumpSymbol.values()) {
            stackCardsOfSymbol(cards, symbol);
        }
    }

    private void stackCardsOfSymbol(LinkedList<TrumpCard> cards, TrumpSymbol symbol) {
        for (TrumpNumber number : TrumpNumber.values()) {
            cards.add(new TrumpCard(number, symbol));
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
