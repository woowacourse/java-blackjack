package blackjack.model.game;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import blackjack.model.card.CardDeck;
import blackjack.model.card.initializer.CardDeckInitializer;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.model.player.User;

public class BlackJackGame {

    private static final int INITIAL_DRAW_AMOUNT = 2;
    private static final int SINGLE_DRAW_AMOUNT = 1;

    private final CardDeck cardDeck;

    public BlackJackGame(final CardDeckInitializer cardDeckInitializer) {
        this.cardDeck = CardDeck.initializeFrom(cardDeckInitializer);
    }

    public void drawInitialCards(final Players players) {
        Dealer dealer = players.getDealer();
        dealer.receiveCards(cardDeck.draw(INITIAL_DRAW_AMOUNT));

        players.getUsers()
                .forEach(player -> player.receiveCards(cardDeck.draw(INITIAL_DRAW_AMOUNT)));
    }

    public boolean canDrawMoreCard(final Player player) {
        return player.canDrawMoreCard();
    }

    public void drawOneCard(final Player player) {
        player.receiveCards(cardDeck.draw(SINGLE_DRAW_AMOUNT));
    }

    public Map<User, GameResult> calculateUserGameResults(final Players players) {
        Dealer dealer = players.getDealer();
        return players.getUsers()
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        user -> GameResult.calculateResult(user, dealer),
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
    }

}
