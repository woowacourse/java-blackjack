package view;

import domain.CardNumber;
import domain.CardShape;

public class CardConverter {
    public static String createTrumpCard(CardShape cardShape, CardNumber cardNumber) {
        return CardNumber.numberToText(cardNumber) + CardShape.shapeToText(cardShape);
    }

}
