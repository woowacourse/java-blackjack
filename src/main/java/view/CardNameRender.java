package view;

import domain.card.Card;
import domain.card.CardType;
import domain.card.CardValue;

public class CardNameRender {

    public static String render(Card card) {
        return renderValue(card.getValue()) + renderType(card.getType());
    }

    private static String renderValue(CardValue cardValue) {
        String score = Integer.toString(cardValue.getScore());

        if (cardValue == CardValue.JACK) {
            return "J";
        }

        if (cardValue == CardValue.QUEEN) {
            return "Q";
        }

        if (cardValue == CardValue.KING) {
            return "K";
        }

        if (cardValue == CardValue.ACE) {
            return "A";
        }

        return score;
    }

    private static String renderType(CardType cardType) {
        if (cardType == CardType.HEART) {
            return "하트";
        }

        if (cardType == CardType.SPADE) {
            return "스페이드";
        }

        if (cardType == CardType.CLUB) {
            return "클로버";
        }

        if (cardType == CardType.DIAMOND) {
            return "다이아몬드";
        }

        throw new IllegalArgumentException("매칭되지 않는 카드 타입이 존재합니다.");
    }
}
