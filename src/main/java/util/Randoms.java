package util;

import model.Card;
import model.CardNumber;
import model.Suits;

public class Randoms {
    public static Card pick() {
        int randomNum = (int) (Math.random() * Suits.values().length);
        Suits suits = Suits.values()[randomNum];

        int randomCardNumber = (int) (Math.random() * CardNumber.values().length);
        CardNumber cardNumber = CardNumber.values()[randomCardNumber];

        return Card.of(suits.getName(), cardNumber.getName());
    }
}
