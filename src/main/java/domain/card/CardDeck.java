package domain.card;

import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;

public class CardDeck {
    private static final List<PlayingCard> originalPlayingCards;

    private final Deque<PlayingCard> playingCards;

    static {
        originalPlayingCards = List.copyOf(
                Arrays.stream(Suit.values())
                        .flatMap(CardDeck::getPlayingCardStream)
                        .collect(toList())
        );
    }

    private static Stream<PlayingCard> getPlayingCardStream(Suit suit) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> PlayingCard.of(suit, denomination));
    }

    private CardDeck() {
        List<PlayingCard> newPlayingCards = new ArrayList<>(originalPlayingCards);
        shuffle(newPlayingCards);

        this.playingCards = new ArrayDeque<>(newPlayingCards);
    }

    public static CardDeck newInstance() {
        return new CardDeck();
    }

    public PlayingCard getCard() {
        return playingCards.pop();
    }
}
