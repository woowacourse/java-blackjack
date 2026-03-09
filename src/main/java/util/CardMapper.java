package util;

import domain.card.Card;
import domain.card.CardShape;

import java.util.Map;

public class CardMapper {

    private CardMapper() {
    }

    public static String cardToKorean(Card card) {
        Map<CardShape, String> koreanCardShapeMap = Map.of(
                CardShape.SPADE, "스페이드",
                CardShape.DIAMOND, "다이아몬드",
                CardShape.HEART, "하트",
                CardShape.CLUB, "클로버");
        return card.getCardNumber().getNumber() + koreanCardShapeMap.get(card.getCardShape());
    }
}
