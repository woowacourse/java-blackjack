package model.cardpicker;

import model.Card;
import model.CardNumber;
import model.Suits;

public class RandomCardPicker implements CardPicker {
    @Override
    public Card pick() {
        int randomNumber = (int) (Math.random() * Suits.values().length);
        Suits suits = Suits.values()[randomNumber];

        int randomCardNumber = (int) (Math.random() * CardNumber.values().length);
        CardNumber cardNumber = CardNumber.values()[randomCardNumber];

        return Card.of(suits.getName(), cardNumber.getName());
    }
}
