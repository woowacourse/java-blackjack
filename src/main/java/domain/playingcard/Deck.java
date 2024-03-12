package domain.playingcard;

import java.util.*;

import static domain.playingcard.PlayingCardValue.values;

public class Deck {
    private final Queue<PlayingCard> playingCards;

    Deck(final Queue<PlayingCard> playingCards) {
        this.playingCards = playingCards;
    }

    public static Deck init() {
        Queue<PlayingCard> playingCards = new LinkedList<>();
        Arrays.stream(PlayingCardShape.values())
                .flatMap(playingCardShape -> generateCardByShape(playingCardShape).stream())
                .forEach(playingCards::offer);

        Collections.shuffle((List<?>) playingCards);

        return new Deck(playingCards);
    }

    private static List<PlayingCard> generateCardByShape(final PlayingCardShape playingCardShape) {
        return Arrays.stream(values())
                .map(playingCardValue -> new PlayingCard(playingCardShape, playingCardValue))
                .toList();
    }

    public PlayingCard drawn() {
        return playingCards.poll();
    }
}
