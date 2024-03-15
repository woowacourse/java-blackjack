package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class Judge {

    private Judge() {
    }

    public static void judge(final Dealer dealer, final Players players) {
        players.getPlayers()
                .forEach(player -> judge(dealer, player));
    }

    private static void judge(final Dealer dealer, final Player player) {
        if (dealer.isBust()) {
            judgeWhenDealerBust(dealer, player);
            return;
        }

        if (dealer.isBlackjack()) {
            judgeWhenDealerBlackjack(dealer, player);
            return;
        }

        judgeWhenDealerNormal(dealer, player);
    }

    private static void judgeWhenDealerBust(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return;
        }

        if (player.isBlackjack()) {
            dealerLose(dealer, player);
            return;
        }

        dealerLose(dealer, player);
    }

    private static void judgeWhenDealerBlackjack(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            dealerWins(dealer, player);
            return;
        }

        if (player.isBlackjack()) {
            return;
        }

        dealerWins(dealer, player);
    }

    private static void judgeWhenDealerNormal(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            dealerWins(dealer, player);
            return;
        }

        if (player.isBlackjack()) {
            dealerLose(dealer, player);
            return;
        }

        judgeWhenNormalTogether(dealer, player);
    }

    private static void judgeWhenNormalTogether(final Dealer dealer, final Player player) {
        if (dealer.isSameScore(player.getScore())) {
            return;
        }

        if (dealer.getScore() > player.getScore()) {
            dealerWins(dealer, player);
            return;
        }

        dealerLose(dealer, player);
    }

    private static void dealerWins(final Dealer dealer, final Player player) {
        player.lose(dealer.getDealerProfit());
    }

    private static void dealerLose(final Dealer dealer, final Player player) {
        player.win(dealer.getDealerProfit());
    }
}
