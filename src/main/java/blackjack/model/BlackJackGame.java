package blackjack.model;

import java.util.List;

public class BlackJackGame {

    private final CardDeck cardDeck;
    private final Rule rule;

    public BlackJackGame(final CardDeck cardDeck, final Rule rule) {
        this.cardDeck = cardDeck;
        this.rule = rule;
    }

    public void dealInitialCards(final List<Player> players) {
        players.forEach(player -> {
            drawCard(player);
            drawCard(player);
        });
    }

    public boolean drawCard(final Player player) {
        if (rule.canPlayerDrawMoreCard(player)) {
            player.receiveCards(cardDeck.draw(1));
            return true;
        }
        return false;
    }

}
