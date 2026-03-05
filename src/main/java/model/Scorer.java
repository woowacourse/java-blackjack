package model;

import model.dto.Card;

public class Scorer {
    public static Integer calculate(Card card) {
        return card.cardNumber().getScore();
    }

    public static Integer calculate(Card card, Integer sum) {
        validateAceCard(card);

        if(sum >= 11) {
            return CardNumber.ACE.getScore();
        }
        return CardNumber.ACE.getScore() + 10;
    }

    private static void validateAceCard(Card card) {
        if(!card.cardNumber().equals(CardNumber.ACE)) {
            throw new IllegalArgumentException("Ace 카드가 아닙니다.");
        }
    }
}
