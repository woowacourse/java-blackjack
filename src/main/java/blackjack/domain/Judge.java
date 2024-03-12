package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.BlackjackResult;
import blackjack.dto.PlayerProfit;
import java.util.List;

public class Judge {

    private Judge() {
    }

    public static BlackjackResult judge(final Dealer dealer, final Players players) {
        List<PlayerProfit> playerProfits = players.getPlayers().stream()
                .map(player -> judge(dealer, player))
                .toList();

        return new BlackjackResult(dealer.getProfit(), playerProfits);
    }

    private static PlayerProfit judge(final Dealer dealer, final Player player) {
        if (dealer.isBust()) {
            return judgeWhenDealerBust(dealer, player);
        }

        if (dealer.isBlackjack()) {
            return judgeWhenDealerBlackjack(dealer, player);
        }

        return judgeWhenDealerNormal(dealer, player);
    }

    private static PlayerProfit judgeWhenDealerBust(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return draw(player);
        }

        if (player.isBlackjack()) {
            return dealerLose(dealer, player);
        }

        return dealerLose(dealer, player);
    }

    private static PlayerProfit judgeWhenDealerBlackjack(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return dealerWins(dealer, player);
        }

        if (player.isBlackjack()) {
            return draw(player);
        }

        return dealerWins(dealer, player);
    }

    private static PlayerProfit judgeWhenDealerNormal(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return dealerWins(dealer, player);
        }

        if (player.isBlackjack()) {
            return dealerLose(dealer, player);
        }

        return judgeWhenNormalTogether(dealer, player);
    }

    private static PlayerProfit judgeWhenNormalTogether(
            final Dealer dealer, final Player player) {
        if (dealer.isSameScore(player.getScore())) {
            return draw(player);
        }

        if (dealer.getScore() > player.getScore()) {
            return dealerWins(dealer, player);
        }

        return dealerLose(dealer, player);
    }

    private static PlayerProfit dealerWins(final Dealer dealer, final Player player) {
        player.lose(dealer.getDealerProfit());
        return new PlayerProfit(player.getName(), player.getProfit());
    }

    private static PlayerProfit dealerLose(final Dealer dealer, final Player player) {
        player.win(dealer.getDealerProfit());
        return new PlayerProfit(player.getName(), player.getProfit());
    }

    private static PlayerProfit draw(final Player player) {
        return new PlayerProfit(player.getName(), player.getProfit());
    }
}
