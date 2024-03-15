package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public final class Judge {

    public Judge() {}

    public void judge(final Dealer dealer, final Players players) {
        players.getPlayers()
                .forEach(player -> judge(dealer, player));
    }

    private void judge(final Dealer dealer, final Player player) {
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

    private void judgeWhenDealerBust(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return;
        }

        if (player.isBlackjack()) {
            dealerLose(dealer, player);
            return;
        }

        dealerLose(dealer, player);
    }

    private void judgeWhenDealerBlackjack(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            dealerWins(dealer, player);
            return;
        }

        if (player.isBlackjack()) {
            return;
        }

        dealerWins(dealer, player);
    }

    private void judgeWhenDealerNormal(final Dealer dealer, final Player player) {
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

    private void judgeWhenNormalTogether(final Dealer dealer, final Player player) {
        if (dealer.isSameScore(player.getScore())) {
            return;
        }

        if (dealer.getScore() > player.getScore()) {
            dealerWins(dealer, player);
            return;
        }

        dealerLose(dealer, player);
    }

    private void dealerWins(final Dealer dealer, final Player player) {
        player.lose(dealer.getDealerProfit());
    }

    private void dealerLose(final Dealer dealer, final Player player) {
        player.win(dealer.getDealerProfit());
    }
}
