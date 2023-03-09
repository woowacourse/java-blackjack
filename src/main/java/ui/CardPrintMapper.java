package ui;

import domain.Card;
import domain.CardNumber;
import domain.Symbol;
import java.util.HashMap;
import java.util.Map;

public class CardPrintMapper {
    private final Map<Symbol, String> symbols = new HashMap<>();
    private final Map<CardNumber, String> cardNumbers = new HashMap<>();

    public CardPrintMapper() {
        addSymbols();
        addOrdinaryCardNumber();
        addSpecialCardNumber();
    }

    private void addSymbols() {
        symbols.put(Symbol.CLOVER, "클로버");
        symbols.put(Symbol.DIAMOND, "다이아몬드");
        symbols.put(Symbol.SPADE, "스페이드");
        symbols.put(Symbol.HEART, "하트");
    }

    private void addOrdinaryCardNumber() {
        cardNumbers.put(CardNumber.TWO, "2");
        cardNumbers.put(CardNumber.THREE, "3");
        cardNumbers.put(CardNumber.FOUR, "4");
        cardNumbers.put(CardNumber.FIVE, "5");
        cardNumbers.put(CardNumber.SIX, "6");
        cardNumbers.put(CardNumber.SEVEN, "7");
        cardNumbers.put(CardNumber.EIGHT, "8");
        cardNumbers.put(CardNumber.NINE, "9");
        cardNumbers.put(CardNumber.TEN, "10");
    }

    private void addSpecialCardNumber() {
        cardNumbers.put(CardNumber.KING, "K");
        cardNumbers.put(CardNumber.QUEEN, "Q");
        cardNumbers.put(CardNumber.JACK, "J");
        cardNumbers.put(CardNumber.ACE, "A");
    }

    public String transformToPrintCard(Card card) {
        return cardNumbers.get(card.getCardNumber()) + symbols.get(card.getSymbol());
    }
}
