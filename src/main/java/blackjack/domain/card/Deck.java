package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deck {

    private static final List<PlayingCard> PLAYING_CARDS = new ArrayList<>();
    private static final int POP = 0;

    private final List<PlayingCard> playingCards;

    static {
        for (Suit suit : Suit.values()) {
            Arrays.stream(Denomination.values())
                    .forEach(symbol -> PLAYING_CARDS.add(new PlayingCard(suit, symbol)));
        }
     }

    public Deck(List<PlayingCard> playingCards) {
        this.playingCards = new ArrayList<>(playingCards);
    }

    public static Deck create() {
        return new Deck(PLAYING_CARDS);
    }

    public PlayingCard assignCard(CardShuffleMachine playingCardShuffleMachine) {
        playingCardShuffleMachine.shuffle(playingCards);
        return playingCards.remove(POP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deck deck = (Deck) o;

        return playingCards != null ? playingCards.equals(deck.playingCards) : deck.playingCards == null;
    }

    @Override
    public int hashCode() {
        return playingCards != null ? playingCards.hashCode() : 0;
    }
}
