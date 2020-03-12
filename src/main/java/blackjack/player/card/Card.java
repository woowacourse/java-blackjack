package blackjack.player.card;

import blackjack.player.card.component.CardNumber;
import blackjack.player.card.component.Symbol;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {

    private final Symbol symbol;
    private final CardNumber cardNumber;

    private Card(Symbol symbol, CardNumber cardNumber) {
        this.symbol = symbol;
        this.cardNumber = cardNumber;
    }

    public static Card of(Symbol symbol, CardNumber cardNumber) {
        return CardCache.of(symbol, cardNumber);
    }

    public boolean isAce() {
        return this.cardNumber == CardNumber.ACE;
    }

    public int getScore() {
        return this.cardNumber.getNumber();
    }

    public int getNumber() {
        return cardNumber.getNumber();
    }

    public String getSymbol() {
        return symbol.getName();
    }

    private static class CardCache {
        private static final List<Card> cache;

        static {
            cache = Arrays.stream(CardNumber.values())
                    .flatMap(CardCache::makeCards)
                    .collect(Collectors.toList());
        }

        private CardCache() {
        }

        private static Stream<Card> makeCards(CardNumber cardNumber) {
            return Arrays.stream(Symbol.values())
                    .map(symbol -> new Card(symbol, cardNumber));
        }

        public static Card of(Symbol symbol, CardNumber cardNumber) {
            return cache.stream()
                    .filter(card -> card.symbol == symbol)
                    .filter(card -> card.cardNumber == cardNumber)
                    .findFirst()
                    .orElseThrow(AssertionError::new);
        }
    }

}
