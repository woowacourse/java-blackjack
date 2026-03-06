package model;

import constant.ErrorMessage;
import model.dto.Card;

public class Scorer {
    public static Integer calculate(Card card) {
        if(isAceCard(card)) {
            throw new IllegalArgumentException(ErrorMessage.ACE_CARD_NEED_TOTAL_SCORE.getMessage());
        }
        return card.cardNumber().getScore();
    }

    public static Integer calculate(Card card, Integer sum) {
        if(!isAceCard(card)) {
            throw new IllegalArgumentException(ErrorMessage.NO_ACE_CARD.getMessage());
        }

        if(sum >= 11) {
            return CardNumber.ACE.getScore();
        }
        return CardNumber.ACE.getScore() + 10;
    }

    private static boolean isAceCard(Card card) {
        return card.cardNumber().equals(CardNumber.ACE);
    }
}
