package domain.playingcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static domain.playingcard.PlayingCardValue.SMALL_ACE;
import static domain.playingcard.PlayingCardValue.values;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Deck {
    private final List<PlayingCard> playingCards;

    Deck(final List<PlayingCard> playingCards) {
        this.playingCards = playingCards;
    }

    public static Deck init() {
        return Arrays.stream(PlayingCardShape.values())
                .flatMap(playingCardShape -> generateCardByShape(playingCardShape).stream())
                .collect(collectingAndThen(toList(), Deck::new));
    }

    private static List<PlayingCard> generateCardByShape(final PlayingCardShape playingCardShape) {
        List<PlayingCard> playingCards = new ArrayList<>(Arrays.stream(values())
                .filter(playingCardValue -> playingCardValue != SMALL_ACE)
                .map(playingCardValue -> new PlayingCard(playingCardShape, playingCardValue))
                .toList());
        Collections.shuffle(playingCards);

        return playingCards;
    }

    public PlayingCard drawn() {
        return playingCards.remove(0);
    }
}
