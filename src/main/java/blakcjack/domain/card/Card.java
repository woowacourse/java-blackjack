package blakcjack.domain.card;

import blakcjack.exception.CacheMissException;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Card {
    private static final Map<CardSymbol, Map<CardNumber, Card>> cache = new EnumMap<>(CardSymbol.class);

    static {
        initializeCache();
        fillCacheData();
    }

    private static void initializeCache() {
        for (final CardSymbol cardSymbol : CardSymbol.values()) {
            cache.put(cardSymbol, new EnumMap<>(CardNumber.class));
        }
    }

    private static void fillCacheData() {
        for (final CardSymbol cardSymbol : CardSymbol.values()) {
            fillSameSymbolData(cardSymbol);
        }
    }

    private static void fillSameSymbolData(final CardSymbol cardSymbol) {
        for (final CardNumber cardNumber : CardNumber.values()) {
            cache.get(cardSymbol)
                    .put(cardNumber, new Card(cardSymbol, cardNumber));
        }
    }

    private final CardSymbol cardSymbol;
    private final CardNumber cardNumber;

    private Card(final CardSymbol cardSymbol, final CardNumber cardNumber) {
        this.cardSymbol = cardSymbol;
        this.cardNumber = cardNumber;
    }

    public static Card of(final CardSymbol cardSymbol, final CardNumber cardNumber) {
        return Optional.ofNullable(bringCacheData(cardSymbol, cardNumber))
                .orElseThrow(() -> new CacheMissException(cardSymbol, cardNumber));
    }

    private static Card bringCacheData(final CardSymbol cardSymbol, final CardNumber cardNumber){
        return Optional.ofNullable(cache.get(cardSymbol))
                .orElseThrow(() -> new CacheMissException(cardSymbol, cardNumber))
                .get(cardNumber);
    }

    public boolean isAce() {
        return cardNumber.equals(CardNumber.ACE);
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public int getCardNumberValue() {
        return cardNumber.getValue();
    }

    public CardSymbol getCardSymbol() {
        return cardSymbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardSymbol == card.cardSymbol && cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardSymbol, cardNumber);
    }
}
