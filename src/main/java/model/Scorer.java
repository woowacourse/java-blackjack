package model;

import model.dto.Card;

public class Scorer {
    public static Integer calculate(Card card) {
        if(!isAceCard(card)) {
            throw new IllegalArgumentException("Ace 카드는 전체 점수가 필요합니다.");
        }
        return card.cardNumber().getScore();
    }

    public static Integer calculate(Card card, Integer sum) {
        if(isAceCard(card)) {
            throw new IllegalArgumentException("Ace 카드가 아닙니다.");
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
