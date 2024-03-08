package view;

import domain.constant.GameResult;
import domain.playingcard.PlayingCardShape;
import domain.playingcard.PlayingCardValue;

import java.util.Map;

import static domain.constant.GameResult.WIN;
import static domain.playingcard.PlayingCardShape.*;
import static domain.playingcard.PlayingCardValue.*;
import static java.util.Map.entry;

public final class OutputFormatConverter {
    private static final Map<PlayingCardShape, String> PLAYING_CARD_SHAPE_STRING = Map.of(
            SPADE, "스페이드", DIAMOND, "다이아몬드", CLOVER, "클로버", HEART, "하트"
    );
    private static final Map<PlayingCardValue, String> PLAYING_CARD_VALUE_STRING = Map.ofEntries(
            entry(SMALL_ACE, "A"),
            entry(DEFAULT_ACE, "A"),
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

    public static String convertGameResultToString(final GameResult gameResult) {
        if (gameResult == WIN) {
            return "승";
        }
        return "패";
    }
}
