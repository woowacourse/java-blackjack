package domain.card;

import static domain.card.CardType.Score.*;
import static domain.card.CardType.Type.*;

import java.util.Arrays;
import java.util.List;

public enum CardType {

    DIAMOND_ACE(DIAMOND, ACE),
    DIAMOND_2(DIAMOND, TWO),
    DIAMOND_3(DIAMOND, THREE),
    DIAMOND_4(DIAMOND, FOUR),
    DIAMOND_5(DIAMOND, FIVE),
    DIAMOND_6(DIAMOND, SIX),
    DIAMOND_7(DIAMOND, SEVEN),
    DIAMOND_8(DIAMOND, EIGHT),
    DIAMOND_9(DIAMOND, NINE),
    DIAMOND_10(DIAMOND, TEN),
    DIAMOND_J(DIAMOND, JACK),
    DIAMOND_Q(DIAMOND, QUEEN),
    DIAMOND_K(DIAMOND, KING),

    HEART_ACE(HEART, ACE),
    HEART_2(HEART, TWO),
    HEART_3(HEART, THREE),
    HEART_4(HEART, FOUR),
    HEART_5(HEART, FIVE),
    HEART_6(HEART, SIX),
    HEART_7(HEART, SEVEN),
    HEART_8(HEART, EIGHT),
    HEART_9(HEART, NINE),
    HEART_10(HEART, TEN),
    HEART_J(HEART, JACK),
    HEART_Q(HEART, QUEEN),
    HEART_K(HEART, KING),

    SPADE_ACE(SPADE, ACE),
    SPADE_2(SPADE, TWO),
    SPADE_3(SPADE, THREE),
    SPADE_4(SPADE, FOUR),
    SPADE_5(SPADE, FIVE),
    SPADE_6(SPADE, SIX),
    SPADE_7(SPADE, SEVEN),
    SPADE_8(SPADE, EIGHT),
    SPADE_9(SPADE, NINE),
    SPADE_10(SPADE, TEN),
    SPADE_J(SPADE, JACK),
    SPADE_Q(SPADE, QUEEN),
    SPADE_K(SPADE, KING),

    CLOVER_ACE(CLOVER, ACE),
    CLOVER_2(CLOVER, TWO),
    CLOVER_3(CLOVER, THREE),
    CLOVER_4(CLOVER, FOUR),
    CLOVER_5(CLOVER, FIVE),
    CLOVER_6(CLOVER, SIX),
    CLOVER_7(CLOVER, SEVEN),
    CLOVER_8(CLOVER, EIGHT),
    CLOVER_9(CLOVER, NINE),
    CLOVER_10(CLOVER, TEN),
    CLOVER_J(CLOVER, JACK),
    CLOVER_Q(CLOVER, QUEEN),
    CLOVER_K(CLOVER, KING);

    private final Type type;
    private final Score score;

    CardType(final Type type, final Score score) {
        this.type = type;
        this.score = score;
    }

    public static List<CardType> getCardTypes() {
        return Arrays.stream(CardType.values()).toList();
    }

    public Type getType() {
        return type;
    }

    public Score getScore() {
        return score;
    }

    public enum Type {
        HEART("하트"),
        SPADE("스페이드"),
        CLOVER("클로버"),
        DIAMOND("다이아몬드");

        private final String name;

        Type(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum Score {
        ACE(1, "A"),
        TWO(2, "2"),
        THREE(3, "3"),
        FOUR(4, "4"),
        FIVE(5, "5"),
        SIX(6, "6"),
        SEVEN(7, "7"),
        EIGHT(8, "8"),
        NINE(9, "9"),
        TEN(10, "10"),
        JACK(10, "J"),
        QUEEN(10, "Q"),
        KING(10, "K");

        private final int value;
        private final String symbol;

        Score(int value, String symbol) {
            this.value = value;
            this.symbol = symbol;
        }

        public int getValue() {
            return value;
        }

        public String getSymbol() {
            return symbol;
        }
    }
}
