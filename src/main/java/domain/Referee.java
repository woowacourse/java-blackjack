package domain;

import java.util.Map;

public class Referee {

    public static void decideWinner(Player player, Dealer dealer, Map<Gambler, Integer> result) {
        if (isPlayerWin(player, dealer)) {
            result.put(player, 1);
        }

        if (isDealerWin(player, dealer)) {
            result.put(player, 0);
            result.replace(dealer, result.get(dealer) + 1);
        }
    }

    private static boolean isPlayerWin(Player player, Dealer dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        return (dealerScore <= playerScore && !player.isBustedGambler()
                || (dealer.isBustedGambler() && !player.isBustedGambler()));
    }

    private static boolean isDealerWin(Player player, Dealer dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        return dealerScore > playerScore || player.isBustedGambler();
    }
}
