package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public final class Referee {

    public Referee() {}

    public void determinePlayersResult(final Dealer dealer, final Players players) {
        players.getPlayers()
                .forEach(player -> determinePlayerResult(dealer, player));
    }

    private void determinePlayerResult(final Dealer dealer, final Player player) {
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
            loseForDealer(dealer, player);
            return;
        }

        loseForDealer(dealer, player);
    }

    private void judgeWhenDealerBlackjack(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            winForDealer(dealer, player);
            return;
        }

        if (player.isBlackjack()) {
            return;
        }

        winForDealer(dealer, player);
    }

    private void judgeWhenDealerNormal(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            winForDealer(dealer, player);
            return;
        }

        if (player.isBlackjack()) {
            loseForDealer(dealer, player);
            return;
        }

        judgeWhenNormalTogether(dealer, player);
    }

    private void judgeWhenNormalTogether(final Dealer dealer, final Player player) {
        if (dealer.isSameScore(player.getScore())) {
            return;
        }

        if (dealer.getScore() > player.getScore()) {
            winForDealer(dealer, player);
            return;
        }

        loseForDealer(dealer, player);
    }

    private void winForDealer(final Dealer dealer, final Player player) {
        player.lose(dealer.getDealerProfit());
    }

    private void loseForDealer(final Dealer dealer, final Player player) {
        player.win(dealer.getDealerProfit());
    }
}
