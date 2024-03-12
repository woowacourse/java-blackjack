package domain;

import domain.constant.CardNumber;
import domain.constant.CardType;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import view.CardNumberView;
import view.CardTypeView;

public class Card {
    private static final Map<String, Card> CARD_POOL = Arrays.stream(CardType.values())
            .flatMap(type -> Arrays.stream(CardNumber.values()).map(number -> new Card(type, number)))
            .collect(Collectors.toMap(Card::toString, card -> card));
    private final CardType cardType;
    private final CardNumber cardNumber;

    private Card(CardType cardType, CardNumber cardNumber) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
    }

    public static Card getCard(CardType cardType, CardNumber cardNumber) {
        validateNull(cardType, cardNumber);
        return CARD_POOL.get(toKey(cardType, cardNumber));
    }

    private static String toKey(CardType cardType, CardNumber cardNumber) {
        return CardNumberView.getViewByNumber(cardNumber).getSymbol()
                + CardTypeView.getViewByType(cardType).getSymbol();
    }

    private static void validateNull(CardType cardType, CardNumber cardNumber) {
        if (cardType == null || cardNumber == null) {
            throw new IllegalArgumentException("null을 인자로 받을 수 없습니다.");
        }
    }

    public boolean isAce() {
        return cardNumber.equals(CardNumber.ACE);
    }

    public CardType getCardType() {
        return cardType;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return toKey(cardType, cardNumber);
    }
}
