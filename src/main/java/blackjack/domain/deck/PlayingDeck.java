package blackjack.domain.deck;

import blackjack.domain.card.Card;

import java.util.*;

public class PlayingDeck {

    private final Queue<Card> playingDeck;

    public PlayingDeck(List<Card> playingDeck) {
        List<Card> copyOfPlayingDeck = new ArrayList<>(playingDeck);
        Collections.shuffle(copyOfPlayingDeck);
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
}
