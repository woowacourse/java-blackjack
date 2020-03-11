package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private static final String CARD_NO_EXSIST_MESSAGE = "적절한 number 또는 symbol의 카드가 존재하지 않습니다.";
    private final int number;
    private final Symbol symbol;

    private Card(int number, Symbol symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    public static Card of(int number, Symbol symbol) {
        return CardCache.cards
                .stream()
                .filter(card -> card.number == number)
                .filter(card -> card.symbol == symbol)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(CARD_NO_EXSIST_MESSAGE));
    }

    public static List<Card> getAllCards() {
        return CardCache.cards;
    }

    public int getNumber() {
        return number;
    }

    private static class CardCache {
        private static final List<Card> cards = new ArrayList<>();
        private static final int MIN = 1;
        private static final int MAX = 12;

        static {
            for (int number = MIN; number <= MAX; number++) {
                cards.addAll(generateAllSymbolCard(number));
            }
        }

        private static List<Card> generateAllSymbolCard(int number) {
            List<Card> allSymbolCards = new ArrayList<>();
            for (Symbol symbol : Symbol.values()) {
                allSymbolCards.add(new Card(number, symbol));
            }
            return allSymbolCards;
        }
    }
}
