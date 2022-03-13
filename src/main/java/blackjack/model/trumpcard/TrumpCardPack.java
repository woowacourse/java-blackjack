package blackjack.model.trumpcard;

import java.util.Collections;
import java.util.Stack;

public class TrumpCardPack {
    private static final String ERROR_EMPTY = "[ERROR] 더 이상 카드를 꺼낼 수 없습니다.";

    private final Stack<TrumpCard> values;

    public TrumpCardPack() {
        this.values = createCards();
    }

    private Stack<TrumpCard> createCards() {
        Stack<TrumpCard> cards = new Stack<>();
        stackCards(cards);
        Collections.shuffle(cards);
        return cards;
    }

    private void stackCards(Stack<TrumpCard> cards) {
        for (TrumpSymbol symbol : TrumpSymbol.values()) {
            stackCardsOfSymbol(cards, symbol);
        }
    }

    private void stackCardsOfSymbol(Stack<TrumpCard> cards, TrumpSymbol symbol) {
        for (TrumpNumber number : TrumpNumber.values()) {
            cards.push(new TrumpCard(number, symbol));
        }
    }

    public TrumpCard draw() {
        checkEmpty();
        return this.values.pop();
    }

    private void checkEmpty() {
        if (this.values.empty()) {
            throw new IllegalArgumentException(ERROR_EMPTY);
        }
    }
}
