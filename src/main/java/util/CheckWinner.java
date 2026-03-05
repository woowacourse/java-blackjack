package util;

import domain.Dealer;
import domain.Outcome;
import domain.Player;
import domain.Players;

public class checkWinner {
    public static void decideWinner(Dealer dealer, Players players) {
        setDealerResultScore(dealer);
        for (int i = 0; i < players.getSize(); i++) {
            compareWithDealer(dealer, players.getPlayer(i));
        }
    }

    private static void setDealerResultScore(Dealer dealer) {
        dealer.setResultScore(dealer.getResult());
        if (dealer.getScore().isBust()) {
            dealer.setResultScore(-1);
        }
    }

    private static void compareWithDealer(Dealer dealer, Player player) {
        setPlayerResultScore(player);
        if (dealer.getResultScore() == player.getResultScore()) {
            applyOutcome(dealer, player, Outcome.무, Outcome.무);
            return;
        }
        if (dealer.getResultScore() > player.getResultScore()) {
            applyOutcome(dealer, player, Outcome.승, Outcome.패);
            return;
        }
        applyOutcome(dealer, player, Outcome.패, Outcome.승);
    }

    private static void setPlayerResultScore(Player player) {
        player.setResultScore(player.getResult());
        if (player.getScore().isBust()) {
            player.setResultScore(-1);
        }
    }

    private static void applyOutcome(Dealer dealer, Player player, Outcome dealerOutcome, Outcome playerOutcome) {
        dealer.addResult(dealerOutcome);
        player.setOutcome(playerOutcome);
    }
}
