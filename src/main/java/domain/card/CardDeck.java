package domain.card;

import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class CardDeck {
    private static final List<PlayingCard> originalPlayingCards;

    private final Stack<PlayingCard> playingCards;

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
        Stack<PlayingCard> playingCards = new Stack<>();
        playingCards.addAll(originalPlayingCards);
        shuffle(playingCards);

        this.playingCards = playingCards;
    }

    public static CardDeck getInstance() {
        return new CardDeck();
    }

    public PlayingCard getCard() {
        return playingCards.pop();
    }
}
