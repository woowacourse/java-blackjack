package domain.playingcard;

import java.util.Arrays;
import java.util.List;

import static domain.playingcard.PlayingCardValue.values;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public record PlayingCards(List<PlayingCard> value) {

    public static PlayingCards init() {
        return Arrays.stream(PlayingCardShape.values())
                .flatMap(playingCardShape -> generateCardByShape(playingCardShape).stream())
                .collect(collectingAndThen(toList(), PlayingCards::new));
    }

    private static List<PlayingCard> generateCardByShape(final PlayingCardShape playingCardShape) {
        return Arrays.stream(values())
                .map(playingCardValue -> new PlayingCard(playingCardShape, playingCardValue))
                .toList();
    }
}
