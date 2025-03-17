package domain.card;

import static domain.card.Rank.ACE;
import static domain.card.Rank.EIGHT;
import static domain.card.Rank.FIVE;
import static domain.card.Rank.FOUR;
import static domain.card.Rank.JACK;
import static domain.card.Rank.KING;
import static domain.card.Rank.NINE;
import static domain.card.Rank.QUEEN;
import static domain.card.Rank.SEVEN;
import static domain.card.Rank.SIX;
import static domain.card.Rank.TEN;
import static domain.card.Rank.THREE;
import static domain.card.Rank.TWO;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Card {
    SPADE_A(SPADE, ACE),     DIAMOND_A(DIAMOND, ACE),    HEART_A(HEART, ACE),    CLOVER_A(CLOVER, ACE),
    SPADE_2(SPADE, TWO),     DIAMOND_2(DIAMOND, TWO),    HEART_2(HEART, TWO),    CLOVER_2(CLOVER, TWO),
    SPADE_3(SPADE, THREE),   DIAMOND_3(DIAMOND, THREE),  HEART_3(HEART, THREE),  CLOVER_3(CLOVER, THREE),
    SPADE_4(SPADE, FOUR),    DIAMOND_4(DIAMOND, FOUR),   HEART_4(HEART, FOUR),   CLOVER_4(CLOVER, FOUR),
    SPADE_5(SPADE, FIVE),    DIAMOND_5(DIAMOND, FIVE),   HEART_5(HEART, FIVE),   CLOVER_5(CLOVER, FIVE),
    SPADE_6(SPADE, SIX),     DIAMOND_6(DIAMOND, SIX),    HEART_6(HEART, SIX),    CLOVER_6(CLOVER, SIX),
    SPADE_7(SPADE, SEVEN),   DIAMOND_7(DIAMOND, SEVEN),  HEART_7(HEART, SEVEN),  CLOVER_7(CLOVER, SEVEN),
    SPADE_8(SPADE, EIGHT),   DIAMOND_8(DIAMOND, EIGHT),  HEART_8(HEART, EIGHT),  CLOVER_8(CLOVER, EIGHT),
    SPADE_9(SPADE, NINE),    DIAMOND_9(DIAMOND, NINE),   HEART_9(HEART, NINE),   CLOVER_9(CLOVER, NINE),
    SPADE_10(SPADE, TEN),    DIAMOND_10(DIAMOND, TEN),   HEART_10(HEART, TEN),   CLOVER_10(CLOVER, TEN),
    SPADE_J(SPADE, JACK),    DIAMOND_J(DIAMOND, JACK),   HEART_J(HEART, JACK),   CLOVER_J(CLOVER, JACK),
    SPADE_Q(SPADE, QUEEN),   DIAMOND_Q(DIAMOND, QUEEN),  HEART_Q(HEART, QUEEN),  CLOVER_Q(CLOVER, QUEEN),
    SPADE_K(SPADE, KING), DIAMOND_K(DIAMOND, KING), HEART_K(HEART, KING), CLOVER_K(CLOVER, KING)
    ;

    private final Shape shape;
    private final Rank rank;

    Card(Shape shape, Rank rank) {
        this.shape = shape;
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }

    public int getNumber() {
        return rank.getNumber();
    }

    @Override
    public String toString() {
        return rank.getName() + shape.getName();
    }

    public static List<Card> allCards() {
        return new ArrayList<>(Arrays.asList(values()));
    }
}
