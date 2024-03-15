package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.BlackjackResult;
import blackjack.dto.PlayerProfitResult;
import java.util.List;

public class Judge {

    private Judge() {
    }

    public static BlackjackResult judge(final Dealer dealer, final Players players) {
        List<PlayerProfitResult> playerProfits = players.getPlayers().stream()
                .map(player -> judge(dealer, player))
                .toList();

        return new BlackjackResult(dealer.getProfit(), playerProfits);
    }

    private static PlayerProfitResult judge(final Dealer dealer, final Player player) {
        if (dealer.isBust()) {
            return judgeWhenDealerBust(dealer, player);
        }

        if (dealer.isBlackjack()) {
            return judgeWhenDealerBlackjack(dealer, player);
        }

        return judgeWhenDealerNormal(dealer, player);
    }

    private static PlayerProfitResult judgeWhenDealerBust(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return draw(player);
        }

        if (player.isBlackjack()) {
            return dealerLose(dealer, player);
        }

        return dealerLose(dealer, player);
    }

    private static PlayerProfitResult judgeWhenDealerBlackjack(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return dealerWins(dealer, player);
        }

        if (player.isBlackjack()) {
            return draw(player);
        }

        return dealerWins(dealer, player);
    }

    private static PlayerProfitResult judgeWhenDealerNormal(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return dealerWins(dealer, player);
        }

        if (player.isBlackjack()) {
            return dealerLose(dealer, player);
        }

        return judgeWhenNormalTogether(dealer, player);
    }

    private static PlayerProfitResult judgeWhenNormalTogether(
            final Dealer dealer, final Player player) {
        if (dealer.isSameScore(player.getScore())) {
            return draw(player);
        }

        if (dealer.getScore() > player.getScore()) {
            return dealerWins(dealer, player);
        }

        return dealerLose(dealer, player);
    }

    private static PlayerProfitResult dealerWins(final Dealer dealer, final Player player) {
        player.lose(dealer.getDealerProfit());
        return new PlayerProfitResult(player.getName(), player.getProfit());
    }

    private static PlayerProfitResult dealerLose(final Dealer dealer, final Player player) {
        player.win(dealer.getDealerProfit());
        return new PlayerProfitResult(player.getName(), player.getProfit());
    }

    private static PlayerProfitResult draw(final Player player) {
        return new PlayerProfitResult(player.getName(), player.getProfit());
    }
}
