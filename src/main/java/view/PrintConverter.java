package view;

import domain.Card;
import domain.CardType;
import domain.CardValue;

import java.util.Map;

import static domain.CardType.*;
import static domain.CardValue.*;

public class PrintConverter {

    public static final Map<CardValue, String> NUMBER_MAP = Map.ofEntries(
            Map.entry(ACE, "A"), Map.entry(TWO, "2"), Map.entry(THREE, "3"),
            Map.entry(FOUR, "4"), Map.entry(FIVE, "5"), Map.entry(SIX, "6"),
            Map.entry(SEVEN, "7"), Map.entry(EIGHT, "8"), Map.entry(NINE, "9"),
            Map.entry(TEN, "10"), Map.entry(JACK, "J"), Map.entry(QUEEN, "Q"), Map.entry(KING, "K"));
    public static final Map<CardType, String> SUIT_MAP = Map.of(CLOVER, "클로버", DIAMOND, "다이아몬드", HEART,
            "하트", SPADE, "스페이드");

    static String of(Card card) {
        return NUMBER_MAP.get(card.getCardValue()) + SUIT_MAP.get(card.getCardType());
    }
}
