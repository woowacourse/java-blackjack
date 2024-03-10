package domain.playingcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static domain.playingcard.PlayingCardValue.SMALL_ACE;
import static domain.playingcard.PlayingCardValue.values;

public class Deck {
    private final List<PlayingCard> playingCards;

    Deck(final List<PlayingCard> playingCards) {
        this.playingCards = playingCards;
    }

    public static Deck init() {
        List<PlayingCard> playingCards = new ArrayList<>(Arrays.stream(PlayingCardShape.values())
                .flatMap(playingCardShape -> generateCardByShape(playingCardShape).stream())
                .toList());
        Collections.shuffle(playingCards);

        return new Deck(playingCards);
    }

    private static List<PlayingCard> generateCardByShape(final PlayingCardShape playingCardShape) {
        return Arrays.stream(values())
                .filter(playingCardValue -> playingCardValue != SMALL_ACE)
                .map(playingCardValue -> new PlayingCard(playingCardShape, playingCardValue))
                .toList();
    }

    public PlayingCard drawn() {
        return playingCards.remove(0);
    }
}
