package view;

import domain.CardNumber;
import domain.CardShape;

public class CardConverter {
    public static String createTrumpCard(final CardShape cardShape, final CardNumber cardNumber) {
        return CardNumber.numberToText(cardNumber) + CardShape.shapeToText(cardShape);
    }
}
