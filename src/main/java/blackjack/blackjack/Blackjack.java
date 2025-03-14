package blackjack.blackjack;

import blackjack.card.Card;
import blackjack.gamer.Dealer;
import blackjack.gamer.Player;
import blackjack.gamer.Players;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Blackjack {

    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    public void betMoney(final Player player, final String amount) {
        player.bet(amount);
    }

    public void spreadInitCardsToDealer(final Dealer dealer) {
        spreadOneCardToDealer(dealer);
        spreadOneCardToDealer(dealer);
    }

    public void spreadInitCardsToPlayers(final Dealer dealer, final Players players) {
        for (Player player : players.getPlayers()) {
            spreadOneCardToPlayer(dealer, player);
            spreadOneCardToPlayer(dealer, player);
        }
    }

    public void spreadOneCardToDealer(final Dealer dealer) {
        final Card card = dealer.spreadOneCard();
        dealer.receiveCard(card);
    }

    public void spreadOneCardToPlayer(final Dealer dealer, final Player player) {
        final Card card = dealer.spreadOneCard();
        player.receiveCard(card);
    }

    public boolean isPush(Dealer dealer, Players players) {
        if (!dealer.isBlackjack(BLACKJACK_SCORE, BLACKJACK_CARD_COUNT)) {
            return false;
        }
        return players.getPlayers().stream()
                .anyMatch(player -> player.isBlackjack(BLACKJACK_SCORE, BLACKJACK_CARD_COUNT));
    }

    public Map<Player, WinningStatus> calculateWinningResult(
            final boolean isPush,
            final Dealer dealer,
            final Players players
    ) {
        Map<Player, WinningStatus> winningStatus = initWinningStatus(players);
        if (isPush) {
            return calculatePushResult(winningStatus, players);
        }
        for (Player player : players.getPlayers()) {
            final WinningStatus playerWinningStatus = calculateWinningStatus(player, dealer);
            winningStatus.replace(player, playerWinningStatus);
        }
        return winningStatus;
    }

    public void calculateEarnedMoney(
            final Map<Player, WinningStatus> winningResult,
            final Dealer dealer,
            final Players players
    ) {
        calculatePlayersEarnedMoney(winningResult, players);
        calculateDealerEarnedMoney(dealer, players);
    }

    private Map<Player, WinningStatus> initWinningStatus(Players players) {
        Map<Player, WinningStatus> winningStatus = new HashMap<>();
        for (Player player : players.getPlayers()) {
            winningStatus.put(player, WinningStatus.UNDEFINED);
        }
        return winningStatus;
    }

    private Map<Player, WinningStatus> calculatePushResult(final Map<Player, WinningStatus> winningStatus,
                                                           final Players players) {
        for (Player player : players.getPlayers()) {
            winningStatus.replace(player, WinningStatus.LOSE);
            markAsBlackjack(winningStatus, player);
        }
        return winningStatus;
    }

    private void markAsBlackjack(final Map<Player, WinningStatus> winningStatus, final Player player) {
        if (player.isBlackjack(BLACKJACK_SCORE, BLACKJACK_CARD_COUNT)) {
            winningStatus.replace(player, WinningStatus.PUSH);
        }
    }

    private WinningStatus calculateWinningStatus(final Player player, final Dealer dealer) {
        if (player.isBlackjack(BLACKJACK_SCORE, BLACKJACK_CARD_COUNT)) {
            return WinningStatus.BLACKJACK;
        }
        final int dealerSum = dealer.sumCards();
        final int playerSum = player.sumCards();
        if (playerSum > BLACKJACK_SCORE) {
            return WinningStatus.LOSE;
        }
        if (dealerSum > BLACKJACK_SCORE) {
            return WinningStatus.WIN;
        }
        if (playerSum > dealerSum) {
            return WinningStatus.WIN;
        }
        if (playerSum == dealerSum) {
            return WinningStatus.DRAW;
        }
        return WinningStatus.LOSE;
    }

    private static void calculatePlayersEarnedMoney(final Map<Player, WinningStatus> winningResult,
                                                    final Players players) {
        Map<WinningStatus, Consumer<Player>> actionMap = Map.of(
                WinningStatus.WIN, Player::win,
                WinningStatus.DRAW, Player::draw,
                WinningStatus.LOSE, Player::lose,
                WinningStatus.BLACKJACK, Player::blackjack,
                WinningStatus.PUSH, Player::push
        );

        for (Player player : players.getPlayers()) {
            final WinningStatus winningStatus = winningResult.get(player);
            Consumer<Player> action = actionMap.get(winningStatus);
            action.accept(player);
        }
    }

    private static void calculateDealerEarnedMoney(final Dealer dealer, final Players players) {
        final long playersTotalProfit = players.getPlayers().stream()
                .mapToLong(Player::getProfit)
                .sum();
        dealer.updateEarnedMoney(-1 * playersTotalProfit);
    }
}
