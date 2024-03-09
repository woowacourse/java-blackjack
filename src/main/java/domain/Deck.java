package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<PlayingCard> playingCards;

    private Deck(final List<PlayingCard> playingCards) {
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
        return Arrays.stream(PlayingCardValue.values())
                .map(playingCardValue -> new PlayingCard(playingCardShape, playingCardValue))
                .toList();
    }

    public PlayingCard drawn() {
        if (playingCards.size() > 0) {
            return playingCards.remove(0);
        }
        throw new IllegalStateException("덱이 모두 소진되었습니다.");
    }
}
