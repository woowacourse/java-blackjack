package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.Profits;

public class Judge {

    private final Dealer dealer;
    private final Player player;

    public Judge(Dealer dealer, Player player) {
        this.dealer = dealer;
        this.player = player;
    }

    public void judge(final Profits profits) {
        if (dealer.isBust()) {
            judgeWhenDealerBust(profits);
            return;
        }

        if (dealer.isBlackjack()) {
            judgeWhenDealerBlackjack(profits);
            return;
        }

        judgeWhenDealerNormal(profits);
    }

    public void judgeWhenDealerBust(final Profits profits) {
        if (player.isBust()) {
            draw(profits);
            return;
        }

        playerWin(profits);
    }

    public void judgeWhenDealerBlackjack(
            final Profits profits) {
        if (player.isBlackjack()) {
            draw(profits);
            return;
        }

        playerLose(profits);
    }

    public void judgeWhenDealerNormal(
            final Profits profits) {
        if (player.isBust()) {
            playerLose(profits);
            return;
        }

        if (player.isBlackjack()) {
            playerWin(profits);
            return;
        }

        judgeWhenNormalTogether(profits);
    }

    private void judgeWhenNormalTogether(
            final Profits profits) {
        if (dealer.isSameScore(player.getScore())) {
            draw(profits);
            return;
        }

        if (dealer.getScore() > player.getScore()) {
            playerLose(profits);
            return;
        }

        playerWin(profits);
    }

    private void playerLose(
            final Profits profits) {
        profits.addProfit(player, GameResult.LOSE);
    }

    private void playerWin(
            final Profits profits) {
        profits.addProfit(player, GameResult.WIN);
    }

    private void draw(
            final Profits profits) {
        profits.addProfit(player, GameResult.DRAW);
    }
}
