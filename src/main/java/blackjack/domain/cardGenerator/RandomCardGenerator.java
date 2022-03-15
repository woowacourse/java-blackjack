package blackjack.domain.cardGenerator;

import blackjack.domain.card.Denomination;
import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Suit;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomCardGenerator implements CardGenerator {
    static final List<PlayingCard> cardDeck;

    static {
        cardDeck = Arrays.stream(Suit.values())
            .flatMap(RandomCardGenerator::getPlayingCardStream)
            .collect(Collectors.toList());
    }

    private static Stream<PlayingCard> getPlayingCardStream(final Suit suit) {
        return Arrays.stream(Denomination.values())
            .map(denomination -> PlayingCard.of(suit, denomination));
    }

    public Deque<PlayingCard> generate() {
        return getCopiedShuffleDeck();
    }

    private Deque<PlayingCard> getCopiedShuffleDeck() {
        Collections.shuffle(cardDeck);
        return new ArrayDeque<>(cardDeck);
    }
}
