package blackjack.model.game;

import java.util.List;
import java.util.Map;

import blackjack.model.card.CardDeck;
import blackjack.model.card.Cards;
import blackjack.model.card.initializer.CardDeckInitializer;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.model.player.User;

public class BlackJackGame {

    private static final int INITIAL_DRAW_AMOUNT = 2;

    private final CardDeck cardDeck;
    private final BlackJackRule blackJackRule;

    public BlackJackGame(final CardDeckInitializer cardDeckInitializer) {
        this.cardDeck = CardDeck.initializeFrom(cardDeckInitializer);
        this.blackJackRule = new BlackJackRule();
    }

    public void drawInitialCards(final Players players) {
        Dealer dealer = players.getDealer();
        drawCard(dealer, INITIAL_DRAW_AMOUNT);

        players.getUsers()
                .forEach(player -> drawCard(player, INITIAL_DRAW_AMOUNT));
    }

    public Cards openInitialCards(final Player player) {
        return blackJackRule.openInitialCards(player);
    }

    public boolean canDrawMoreCard(final Player player) {
        return blackJackRule.canDrawMoreCard(player);
    }

    public void drawCard(final Player player, final int amount) {
        player.receiveCards(cardDeck.draw(amount));
    }

    public Map<Player, Map<GameResult, Integer>> calculateResult(final Players players) {
        Dealer dealer = players.getDealer();
        List<User> users = players.getUsers();
        return blackJackRule.calculateResult(dealer, users);
    }

}
