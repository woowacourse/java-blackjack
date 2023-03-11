package view;

import static domain.card.Number.A;
import static domain.card.Number.EIGHT;
import static domain.card.Number.FIVE;
import static domain.card.Number.FOUR;
import static domain.card.Number.J;
import static domain.card.Number.K;
import static domain.card.Number.NINE;
import static domain.card.Number.Q;
import static domain.card.Number.SEVEN;
import static domain.card.Number.SIX;
import static domain.card.Number.TEN;
import static domain.card.Number.THREE;
import static domain.card.Number.TWO;
import static domain.card.Suit.CLOVER;
import static domain.card.Suit.DIAMOND;
import static domain.card.Suit.HEART;
import static domain.card.Suit.SPADE;

import domain.GameResult;
import domain.card.Card;
import domain.card.Number;
import domain.card.Suit;
import java.util.Map;

public class Converter {

    public static final Map<Number, String> NUMBER_MAP = Map.ofEntries(
        Map.entry(A, "A"), Map.entry(TWO, "2"), Map.entry(THREE, "3"),
        Map.entry(FOUR, "4"), Map.entry(FIVE, "5"), Map.entry(SIX, "6"),
        Map.entry(SEVEN, "7"), Map.entry(EIGHT, "8"), Map.entry(NINE, "9"),
        Map.entry(TEN, "10"), Map.entry(J, "J"), Map.entry(Q, "Q"), Map.entry(K, "K"));
    public static final Map<Suit, String> SUIT_MAP = Map.of(CLOVER, "클로버", DIAMOND, "다이아몬드", HEART,
        "하트", SPADE, "스페이드");
    public static final Map<GameResult, String> RESULT_MAP = Map.of(GameResult.WIN, "승",
        GameResult.LOSE, "패",
        GameResult.DRAW, "무");

    static String of(Card card) {
        return NUMBER_MAP.get(card.getNumber()) + SUIT_MAP.get(card.getSuit());
    }

    static String of(GameResult gameResult) {
        return RESULT_MAP.get(gameResult);
    }
}
