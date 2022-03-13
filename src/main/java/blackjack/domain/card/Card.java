package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Card {
    private static final List<Card> CACHE = createCards();

    private final CardNumber number;
    private final CardSymbol symbol;

    private Card(CardNumber number, CardSymbol symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    private static List<Card> createCards() {
        return Arrays.stream(CardSymbol.values())
                .flatMap(symbol -> createSymbolCards(symbol).stream())
                .collect(Collectors.toList());
    }

    private static List<Card> createSymbolCards(CardSymbol cardSymbol) {
        return Arrays.stream(CardNumber.values())
                .map(cardNumber -> new Card(cardNumber, cardSymbol))
                .collect(Collectors.toList());
    }

    public static List<Card> createNewCardDeck() {
        return new ArrayList<>(CACHE);
    }

    public static Card valueOf(final CardNumber cardNumber, final CardSymbol cardSymbol) {
        return CACHE.stream()
                .filter(card -> isEqual(cardNumber, cardSymbol, card))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 카드는 없습니다."));

    }

    private static boolean isEqual(final CardNumber cardNumber, final CardSymbol cardSymbol, final Card targetCard) {
        return targetCard.number == cardNumber && targetCard.symbol == cardSymbol;
    }

    public int getCardNumberValue() {
        return number.getCardNumberValue();
    }

    @Override
    public String toString() {
        return number.toString() + symbol.toString();
    }
}
