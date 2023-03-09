package view;

import static domain.Number.A;
import static domain.Number.EIGHT;
import static domain.Number.FIVE;
import static domain.Number.FOUR;
import static domain.Number.J;
import static domain.Number.K;
import static domain.Number.NINE;
import static domain.Number.Q;
import static domain.Number.SEVEN;
import static domain.Number.SIX;
import static domain.Number.TEN;
import static domain.Number.THREE;
import static domain.Number.TWO;
import static domain.Suit.CLOVER;
import static domain.Suit.DIAMOND;
import static domain.Suit.HEART;
import static domain.Suit.SPADE;

import domain.Card;
import domain.GameResult;
import domain.Number;
import domain.Suit;
import java.util.Map;

public class PrintConverter {

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
