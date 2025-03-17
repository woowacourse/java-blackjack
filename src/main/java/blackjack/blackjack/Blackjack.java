package blackjack.blackjack;

import blackjack.card.Card;
import blackjack.gamer.Dealer;
import blackjack.gamer.Player;
import blackjack.gamer.Players;

public class Blackjack {

    public static final int BLACKJACK_SCORE = 21;
    public static final int BLACKJACK_CARD_COUNT = 2;

    public void betMoney(final Player player, final String amount) {
        player.betMoney(amount);
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

    public void calculateState(final Players players, final Dealer dealer) {
        for (Player player : players.getPlayers()) {
            player.calculateState(dealer);
        }

        calculateDealerEarnedMoney(dealer, players);
    }

    private static void calculateDealerEarnedMoney(final Dealer dealer, final Players players) {
        final long playersTotalProfit = players.getPlayers().stream()
                .mapToLong(Player::getProfit)
                .sum();
        dealer.updateEarnedMoney(-1 * playersTotalProfit);
    }
}
