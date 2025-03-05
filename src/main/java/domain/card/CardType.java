package domain.card;

import static domain.card.CardType.Type.*;

import java.util.Arrays;
import java.util.List;

public enum CardType {

    DIAMOND_ACE(DIAMOND, 1),
    DIAMOND_2(DIAMOND, 2),
    DIAMOND_3(DIAMOND, 3),
    DIAMOND_4(DIAMOND, 4),
    DIAMOND_5(DIAMOND, 5),
    DIAMOND_6(DIAMOND, 6),
    DIAMOND_7(DIAMOND, 7),
    DIAMOND_8(DIAMOND, 8),
    DIAMOND_9(DIAMOND, 9),
    DIAMOND_10(DIAMOND, 10),
    DIAMOND_J(DIAMOND, 10),
    DIAMOND_Q(DIAMOND, 10),
    DIAMOND_K(DIAMOND, 10),

    HEART_ACE(HEART, 1),
    HEART_2(HEART, 2),
    HEART_3(HEART, 3),
    HEART_4(HEART, 4),
    HEART_5(HEART, 5),
    HEART_6(HEART, 6),
    HEART_7(HEART, 7),
    HEART_8(HEART, 8),
    HEART_9(HEART, 9),
    HEART_10(HEART, 10),
    HEART_J(HEART, 10),
    HEART_Q(HEART, 10),
    HEART_K(HEART, 10),

    SPADE_ACE(SPADE, 1),
    SPADE_2(SPADE, 2),
    SPADE_3(SPADE, 3),
    SPADE_4(SPADE, 4),
    SPADE_5(SPADE, 5),
    SPADE_6(SPADE, 6),
    SPADE_7(SPADE, 7),
    SPADE_8(SPADE, 8),
    SPADE_9(SPADE, 9),
    SPADE_10(SPADE, 10),
    SPADE_J(SPADE, 10),
    SPADE_Q(SPADE, 10),
    SPADE_K(SPADE, 10),

    CLOVER_ACE(CLOVER, 1),
    CLOVER_2(CLOVER, 2),
    CLOVER_3(CLOVER, 3),
    CLOVER_4(CLOVER, 4),
    CLOVER_5(CLOVER, 5),
    CLOVER_6(CLOVER, 6),
    CLOVER_7(CLOVER, 7),
    CLOVER_8(CLOVER, 8),
    CLOVER_9(CLOVER, 9),
    CLOVER_10(CLOVER, 10),
    CLOVER_J(CLOVER, 10),
    CLOVER_Q(CLOVER, 10),
    CLOVER_K(CLOVER, 10);

    private final Type type;
    private final int score;

    CardType(final Type type, final int score) {
        this.type = type;
        this.score = score;
    }

    public static List<CardType> getCardTypes() {
        return Arrays.stream(CardType.values()).toList();
    }

    public Type getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    public enum Type {
        HEART,
        SPADE,
        CLOVER,
        DIAMOND
    }
}
