package view;

import static domain.card.Denomination.A;
import static domain.card.Denomination.EIGHT;
import static domain.card.Denomination.FIVE;
import static domain.card.Denomination.FOUR;
import static domain.card.Denomination.J;
import static domain.card.Denomination.K;
import static domain.card.Denomination.NINE;
import static domain.card.Denomination.Q;
import static domain.card.Denomination.SEVEN;
import static domain.card.Denomination.SIX;
import static domain.card.Denomination.TEN;
import static domain.card.Denomination.THREE;
import static domain.card.Denomination.TWO;
import static domain.card.Suit.CLOVER;
import static domain.card.Suit.DIAMOND;
import static domain.card.Suit.HEART;
import static domain.card.Suit.SPADE;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import java.util.Map;

public class CardConverter {

    public static final Map<Denomination, String> NUMBER_MAP = Map.ofEntries(
        Map.entry(A, "A"), Map.entry(TWO, "2"), Map.entry(THREE, "3"),
        Map.entry(FOUR, "4"), Map.entry(FIVE, "5"), Map.entry(SIX, "6"),
        Map.entry(SEVEN, "7"), Map.entry(EIGHT, "8"), Map.entry(NINE, "9"),
        Map.entry(TEN, "10"), Map.entry(J, "J"), Map.entry(Q, "Q"), Map.entry(K, "K"));
    public static final Map<Suit, String> SUIT_MAP = Map.of(CLOVER, "클로버", DIAMOND, "다이아몬드", HEART,
        "하트", SPADE, "스페이드");

    static String of(Card card) {
        return NUMBER_MAP.get(card.getNumber()) + SUIT_MAP.get(card.getSuit());
    }
}
