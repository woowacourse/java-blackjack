package blackjack.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Card {
    private final CardNumber cardNumber;
    private final Symbol symbol;
    private static final Map<String, Card> CACHE = new HashMap<>();

    static {
        List<Card> cards = createCards();
        cards.forEach(card -> CACHE.put(card.cardInfo(), card));
    }

    private Card(CardNumber cardNumber, Symbol symbol) {
        this.cardNumber = cardNumber;
        this.symbol = symbol;
    }

    public static Card valueOf(CardNumber cardNumber, Symbol symbol) {
        return CACHE.get(cardNumber.getCardNumberName() + symbol.getSymbolName());
    }

    public int getCardNumberValue() {
        return cardNumber.getCardNumberValue();
    }

    public boolean isAce() {
        return cardNumber == CardNumber.ACE;
    }

    public String cardInfo() {
        return cardNumber.getCardNumberName() + symbol.getSymbolName();
    }

    private static List<Card> createCards() {
        return Arrays.stream(Symbol.values())
                .flatMap(symbol -> createSymbolCards(symbol).stream())
                .collect(Collectors.toList());
    }

    private static List<Card> createSymbolCards(Symbol symbol) {
        return Arrays.stream(CardNumber.values())
                .map(cardNumber -> new Card(cardNumber, symbol))
                .collect(Collectors.toList());
    }

}
