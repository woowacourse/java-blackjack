package blackjack.domain.card;

import java.util.*;

public class Deck {

    private static final Stack<PlayingCard> PLAYING_CARDS = new Stack<>();

    private final Stack<PlayingCard> playingCards;

    static {
        for (Suit suit : Suit.values()) {
            Arrays.stream(Denomination.values())
                    .forEach(symbol -> PLAYING_CARDS.add(new PlayingCard(suit, symbol)));
        }
     }

    public Deck(Stack<PlayingCard> playingCards) {
        this.playingCards = playingCards;
    }

    public static Deck create() {
        return new Deck(PLAYING_CARDS);
    }

    public void shuffle(CardShuffleMachine playingCardShuffleMachine) {
        playingCardShuffleMachine.shuffle(playingCards);
    }

    public PlayingCard assignCard() {
        return pickCard();
    }

    private PlayingCard pickCard() {
        return playingCards.pop();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return Objects.equals(playingCards, deck.playingCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playingCards);
    }
}
