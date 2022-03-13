package domain.card;

import static java.util.stream.Collectors.toList;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;

public class CardDeck {
    private static final int INITIAL_SIZE = 52;
    private static final String CAN_NOT_SHUFFLE_USED_DECK = "사용중인 카드는 다시 섞을 수 없습니다.";
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
        this.playingCards = new ArrayDeque<>(originalPlayingCards);
    }

    public static CardDeck newInstance() {
        return new CardDeck();
    }

    public PlayingCard getCard() {
        return playingCards.pop();
    }

    public CardDeck shuffle() {
        if (playingCards.size() != INITIAL_SIZE) {
            throw new IllegalStateException(CAN_NOT_SHUFFLE_USED_DECK);
        }

        playingCards.clear();
        List<PlayingCard> newPlayingCards = new ArrayList<>(originalPlayingCards);
        Collections.shuffle(newPlayingCards);
        playingCards.addAll(newPlayingCards);
        return this;
    }
}
