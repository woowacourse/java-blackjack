package blackjack.model.deck;

import blackjack.model.card.Card;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PlayingDeck {

    private final Queue<Card> playingDeck;

    public PlayingDeck(List<Card> playingDeck) {
        Collections.shuffle(playingDeck);
        this.playingDeck = new LinkedList<>(playingDeck);
    }

    public Card drawCard() {
        Card card = playingDeck.poll();
        validatePlayingDeckEmpty(card);
        return card;
    }

    private void validatePlayingDeckEmpty(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("[ERROR] 카드를 모두 사용했습니다.");
        }
    }
}
