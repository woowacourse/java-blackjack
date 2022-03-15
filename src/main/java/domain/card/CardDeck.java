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
    private static final List<PlayingCard> ORIGINAL_PLAYING_CARDS;

    private final Deque<PlayingCard> playingPlayingCards;

    static {
        ORIGINAL_PLAYING_CARDS = List.copyOf(
                Arrays.stream(Suit.values())
                        .flatMap(CardDeck::getCardStream)
                        .collect(toList())
        );
    }

    private static Stream<PlayingCard> getCardStream(Suit suit) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> PlayingCard.of(suit, denomination));
    }

    private CardDeck() {
        this.playingPlayingCards = new ArrayDeque<>(ORIGINAL_PLAYING_CARDS);
    }

    private CardDeck(List<PlayingCard> playingPlayingCards) {
        this.playingPlayingCards = new ArrayDeque<>(playingPlayingCards);
    }

    public static CardDeck newInstance() {
        return new CardDeck();
    }

    public PlayingCard drawCard() {
        return playingPlayingCards.pop();
    }

    public CardDeck shuffle() {
        if (playingPlayingCards.size() != INITIAL_SIZE) {
            throw new IllegalStateException(CAN_NOT_SHUFFLE_USED_DECK);
        }

        List<PlayingCard> newPlayingCards = new ArrayList<>(ORIGINAL_PLAYING_CARDS);
        Collections.shuffle(newPlayingCards);
        return new CardDeck(newPlayingCards);
    }
}
