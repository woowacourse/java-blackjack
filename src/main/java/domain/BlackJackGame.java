package domain;

import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.result.PlayerResults;
import domain.result.Result;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackGame {
    private static final int INITIAL_CARD_COUNT = 2;
    private final Deck deck;

    public BlackJackGame(final Deck deck) {
        this.deck = deck;
    }

    public void prepareCards(final Dealer dealer, final Players players) {
        giveInitialCards(dealer);

        for (Player player : players.getPlayers()) {
            giveInitialCards(player);
        }
    }

    private void giveInitialCards(final Gamer gamer) {
        for (int count = 0; count < INITIAL_CARD_COUNT; count++) {
            gamer.receiveInitialCard(deck.draw());
        }
    }

    public void giveCard(final Gamer gamer) {
        gamer.hit(deck.draw());
    }

    public PlayerResults findPlayerResult(final Dealer dealer, final Players players) {
        Map<Player, Result> playerResults = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            Result playerResult = Result.getPlayerResultWith(player, dealer);
            playerResults.put(player, playerResult);
        }
        return new PlayerResults(playerResults);
    }
}
