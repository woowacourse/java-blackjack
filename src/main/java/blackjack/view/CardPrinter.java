package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class CardPrinter {
    private static final EnumMap<CardSymbol, String> symbolBoard = initializeSymbol();
    private static final EnumMap<CardValue, String> valueBoard = initializeValue();
    private static final String JOIN_SPLITER = ", ";

    public static String printCards(final List<Card> cards) {
        return cards.stream()
                    .map(CardPrinter::printCard)
                    .collect(Collectors.joining(JOIN_SPLITER));
    }

    public static String printCard(final Card card) {
        return valueBoard.get(card.cardValue()) + symbolBoard.get(card.cardSymbol());
    }

    private static EnumMap<CardSymbol, String> initializeSymbol() {
        final EnumMap<CardSymbol, String> symbolBoard = new EnumMap<>(CardSymbol.class);
        symbolBoard.put(CardSymbol.HEART, "하트");
        symbolBoard.put(CardSymbol.SPADE, "스페이드");
        symbolBoard.put(CardSymbol.CLOVER, "클로버");
        symbolBoard.put(CardSymbol.DIAMOND, "다이아몬드");
        return symbolBoard;
    }

    private static EnumMap<CardValue, String> initializeValue() {
        final EnumMap<CardValue, String> valueBoard = new EnumMap<>(CardValue.class);
        valueBoard.put(CardValue.ACE, "A");
        valueBoard.put(CardValue.TWO, "2");
        valueBoard.put(CardValue.THREE, "3");
        valueBoard.put(CardValue.FOUR, "4");
        valueBoard.put(CardValue.FIVE, "5");
        valueBoard.put(CardValue.SIX, "6");
        valueBoard.put(CardValue.SEVEN, "7");
        valueBoard.put(CardValue.EIGHT, "8");
        valueBoard.put(CardValue.NINE, "9");
        valueBoard.put(CardValue.TEN, "10");
        valueBoard.put(CardValue.JACK, "J");
        valueBoard.put(CardValue.QUEEN, "Q");
        valueBoard.put(CardValue.KING, "K");
        return valueBoard;
    }
}
