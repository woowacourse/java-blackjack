package model;

import model.dto.Card;

public class Scorer {
    public static Integer calculate(Card card) {
        return card.cardNumber().getScore();
    }
}
