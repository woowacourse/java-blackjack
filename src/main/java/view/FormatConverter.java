package view;

import domain.PlayingCardShape;
import domain.PlayingCardValue;

import java.util.Map;

import static domain.PlayingCardShape.*;
import static domain.PlayingCardValue.*;
import static java.util.Map.entry;

public final class FormatConverter {
    private static final String YES = "y";
    private static final String NO = "n";
    private static final Map<PlayingCardShape, String> PLAYING_CARD_SHAPE_STRING = Map.of(
            SPADE, "스페이드", DIAMOND, "다이아몬드", CLOVER, "클로버", HEART, "하트"
    );
    private static final Map<PlayingCardValue, String> PLAYING_CARD_VALUE_STRING = Map.ofEntries(
            entry(ACE, "A"),
            entry(TWO, "2"),
            entry(THREE, "3"),
            entry(FOUR, "4"),
            entry(FIVE, "5"),
            entry(SIX, "6"),
            entry(SEVEN, "7"),
            entry(EIGHT, "8"),
            entry(NINE, "9"),
            entry(TEN, "10"),
            entry(JACK, "J"),
            entry(QUEEN, "Q"),
            entry(KING, "K")
    );

    public static String convertPlayingCardShapeToString(final PlayingCardShape playingCardShape) {
        return PLAYING_CARD_SHAPE_STRING.get(playingCardShape);
    }

    public static String convertPlayingCardValueToString(final PlayingCardValue playingCardValue) {
        return PLAYING_CARD_VALUE_STRING.get(playingCardValue);
    }

    public static boolean convertInputToDecision(final String input) {
        if (input.equals(YES)) {
            return true;
        }

        if (input.equals(NO)) {
            return false;
        }

        throw new IllegalArgumentException(String.format("%s는 올바른 입력이 아닙니다. 카드를 받을지 여부는 y/n만 입력할 수 있습니다.", input));
    }
}
