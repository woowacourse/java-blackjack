package blackjack.model.game;

import blackjack.model.card.CardDeck;
import blackjack.model.card.initializer.CardDeckInitializer;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.User;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private static final int INITIAL_DRAW_AMOUNT = 2;
    private static final int SINGLE_DRAW_AMOUNT = 1;

    private final CardDeck cardDeck;

    public BlackJackGame(final CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public BlackJackGame(final CardDeckInitializer cardDeckInitializer) {
        this.cardDeck = CardDeck.initializeFrom(cardDeckInitializer);
    }

    public void dealInitialCards(final List<Player> players) {
        players.forEach(player -> {
            drawCard(player, INITIAL_DRAW_AMOUNT);
        });
    }

    public boolean drawMoreCard(final Player player) {
        if (player.canDrawMoreCard()) {
            drawCard(player, SINGLE_DRAW_AMOUNT);
            return true;
        }
        return false;
    }

    private void drawCard(final Player player, final int amount) {
        player.receiveCards(cardDeck.draw(amount));
    }

    public Map<Player, ResultStatistic> calculateResult(final Dealer dealer, final List<User> users) {
        Map<Player, ResultStatistic> results = new LinkedHashMap<>();
        results.put(dealer, new ResultStatistic());
        users.forEach(
                user -> {
                    results.put(user, new ResultStatistic());
                    results.get(dealer).add(Result.findWinner(dealer, user));
                    results.get(user).add(Result.findWinner(user, dealer));
                }
        );

        return results;
    }
}
