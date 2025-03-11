package blackjack.model.game;

import blackjack.model.card.CardDeck;
import blackjack.model.card.initializer.CardDeckInitializer;
import blackjack.model.player.BlackJackPlayer;
import java.util.List;

public class BlackJackGame {

    private static final int INITIAL_DRAW_AMOUNT = 2;
    private static final int SINGLE_DRAW_AMOUNT = 1;

    private final CardDeck cardDeck;

    public BlackJackGame(final CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public BlackJackGame(final CardDeckInitializer cardDeckInitializer) {
        this(CardDeck.initializeFrom(cardDeckInitializer));
    }

    public void dealInitialCards(final List<BlackJackPlayer> blackJackPlayers) {
        blackJackPlayers.forEach(player -> {
            drawCard(player, INITIAL_DRAW_AMOUNT);
        });
    }

    public boolean drawMoreCard(final BlackJackPlayer blackJackPlayer) {
        if (blackJackPlayer.canDrawMoreCard()) {
            drawCard(blackJackPlayer, SINGLE_DRAW_AMOUNT);
            return true;
        }
        return false;
    }

    private void drawCard(final BlackJackPlayer blackJackPlayer, final int amount) {
        blackJackPlayer.receiveCards(cardDeck.draw(amount));
    }
}
