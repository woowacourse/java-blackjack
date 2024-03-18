package blackjack.domain.deck;

import blackjack.domain.card.Card;
import blackjack.domain.deck.shuffle.Shuffle;

import java.util.*;

public class PlayingDeck {
    private final Queue<Card> playingDeck;

    public PlayingDeck(List<Card> playingDeck, Shuffle shuffle) {
        List<Card> copyOfPlayingDeck = new ArrayList<>(playingDeck);
        shuffle.shuffle(copyOfPlayingDeck);
        this.playingDeck = new LinkedList<>(copyOfPlayingDeck);
    }

    public Card drawCard() {
        validatePlayingDeckEmpty();
        return playingDeck.poll();
    }

    private void validatePlayingDeckEmpty() {
        if (playingDeck.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 카드를 모두 사용했습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingDeck that = (PlayingDeck) o;
        return Objects.equals(playingDeck, that.playingDeck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playingDeck);
    }
}
