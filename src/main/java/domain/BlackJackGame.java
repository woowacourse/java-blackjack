package domain;

import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.gamer.Players;
import exception.CardReceiveException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackGame {
    private static final int INITIAL_CARD_COUNT = 2;
    private final Deck deck;
    private final BetAmounts betAmounts;

    public BlackJackGame(final Deck deck, final BetAmounts betAmounts) {
        this.deck = deck;
        this.betAmounts = betAmounts;
    }

    public void bet(final Player player, int amount) {
        betAmounts.addBetAmount(player, amount);
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
        if (!gamer.canHit()) {
            throw new CardReceiveException(CardReceiveException.CAN_NOT_RECEIVE_CARD);
        }
        gamer.hit(deck.draw());
    }

    public Map<Player, Integer> createPlayersResult(final Dealer dealer, final Players players) {
        Map<Player, Integer> playerResults = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            int playerProfit = betAmounts.calculatePlayerBetProfit(player, dealer);
            playerResults.put(player, playerProfit);
        }
        return playerResults;
    }

    public int calculateDealerProfit(final Dealer dealer, final Players players) {
        int dealerProfit = 0;
        for (Player player : players.getPlayers()) {
            dealerProfit += betAmounts.calculateDealerBetProfit(player, dealer);
        }
        return dealerProfit;
    }
}
