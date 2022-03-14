package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayingCards {

    private static final List<PlayingCard> PLAYING_CARDS = new ArrayList<>();

    private final PlayingCardPickMachine playingCardPickMachine;

    static {
        for (Suit suit : Suit.values()) {
            Arrays.stream(Denomination.values())
                    .forEach(symbol -> PLAYING_CARDS.add(new PlayingCard(suit, symbol)));
        }
     }

    public PlayingCards(PlayingCardPickMachine playingCardPickMachine) {
        this.playingCardPickMachine = playingCardPickMachine;
    }

    public PlayingCard assignCard() {
        return PLAYING_CARDS.get(playingCardPickMachine.assignIndex());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayingCards playingCards1 = (PlayingCards) o;

        return PLAYING_CARDS != null ? PLAYING_CARDS.equals(playingCards1.PLAYING_CARDS) : playingCards1.PLAYING_CARDS == null;
    }

    @Override
    public int hashCode() {
        return PLAYING_CARDS != null ? PLAYING_CARDS.hashCode() : 0;
    }
}
