package blackjack.domain.card;

import blackjack.domain.cardGenerator.CardGenerator;
import blackjack.domain.player.Player;
import java.util.Stack;

public class CardDeck {

    private final Stack<PlayingCard> playingCards;

    public CardDeck(final CardGenerator cardGenerator) {
        this.playingCards = cardGenerator.generate();
    }

    public void drawTo(Player player) {
        player.receiveCard(playingCards.pop());
    }

    public PlayingCard pop() {
        return playingCards.pop();
    }
}
