package domain.playingcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static domain.playingcard.PlayingCardValue.values;

public class PlayingCards {
    private static final List<PlayingCard> value = PlayingCards.init();

    private PlayingCards() {
    }

    private static List<PlayingCard> init() {
        return new ArrayList<>(Arrays.stream(PlayingCardShape.values())
                .flatMap(playingCardShape -> generateCardByShape(playingCardShape).stream())
                .toList());
    }

    private static List<PlayingCard> generateCardByShape(final PlayingCardShape playingCardShape) {
        return Arrays.stream(values())
                .map(playingCardValue -> new PlayingCard(playingCardShape, playingCardValue))
                .toList();
    }

    public static List<PlayingCard> getValue() {
        return value;
    }
}
