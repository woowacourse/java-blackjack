package blackjack.model.game;

import java.util.List;

import blackjack.model.card.CardDeck;
import blackjack.model.card.initializer.CardDeckInitializer;
import blackjack.model.player.Player;

public class BlackJackGame {

    private static final int INITIAL_DRAW_AMOUNT = 2;
    private static final int SINGLE_DRAW_AMOUNT = 1;

    private final CardDeck cardDeck;
    private final BlackJackRule blackJackRule;

    public BlackJackGame(final CardDeckInitializer cardDeckInitializer, final BlackJackRule blackJackRule) {
        this.cardDeck = CardDeck.initializeFrom(cardDeckInitializer);
        this.blackJackRule = blackJackRule;
    }

    public void dealInitialCards(final List<Player> players) {
        players.forEach(player -> {
            drawCard(player, INITIAL_DRAW_AMOUNT);
        });
    }

    public boolean drawMoreCard(final Player player) {
        if (blackJackRule.canDrawMoreCard(player)) {
            drawCard(player, SINGLE_DRAW_AMOUNT);
            return true;
        }
        return false;
    }

    private void drawCard(final Player player, final int amount) {
        player.receiveCards(cardDeck.draw(amount));
    }

}
