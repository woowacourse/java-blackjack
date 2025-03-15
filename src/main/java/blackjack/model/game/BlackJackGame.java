package blackjack.model.game;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import blackjack.model.card.CardDeck;
import blackjack.model.card.Cards;
import blackjack.model.card.initializer.CardDeckInitializer;
import blackjack.model.player.Player;

public class BlackJackGame {

    private static final int INITIAL_DRAW_AMOUNT = 2;
    private static final int SINGLE_DRAW_AMOUNT = 1;

    private final CardDeck cardDeck;
    private final BlackJackRule blackJackRule;

    public BlackJackGame(final CardDeckInitializer cardDeckInitializer) {
        this.cardDeck = CardDeck.initializeFrom(cardDeckInitializer);
        this.blackJackRule = new BlackJackRule();
    }

    public void dealInitialCards(final List<Player> players) {
        players.forEach(player -> {
            drawCard(player, INITIAL_DRAW_AMOUNT);
        });
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

    public Map<Player, Map<GameResult, Integer>> calculateResult(final List<Player> players) {
        Player dealer = players.getFirst();
        List<Player> users = players.stream()
                .skip(1L)
                .toList();
        return blackJackRule.calculateResult(dealer, users);
    }

    public Map<Player, Integer> calculateOptimalPoints(final List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        blackJackRule::calculateOptimalPoint,
                        (x, y) -> y,
                        LinkedHashMap::new
                ));
    }

}
