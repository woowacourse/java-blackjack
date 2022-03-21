package blackjack.domain.card;

import blackjack.domain.cardGenerator.CardGenerator;
import blackjack.domain.player.Player;
import java.util.Deque;

public class CardDeck {

    private static final int DRAW_CARD_START_INDEX = 0;
    private final Deque<PlayingCard> playingCards;

    public CardDeck(final CardGenerator cardGenerator) {
        this.playingCards = cardGenerator.generate();
    }

    public void drawTo(Player player, final int count) {
        for (int i = DRAW_CARD_START_INDEX; i < count; i++) {
            player.receiveCard(this);
        }
    }

    public PlayingCard pop() {
        return playingCards.pop();
    }
}
