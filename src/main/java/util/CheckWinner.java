package util;

import domain.Dealer;
import domain.Outcome;
import domain.Player;
import dto.DealerPlayerDTO;
import dto.DealerPlayersDTO;

public class CheckWinner {
    public static void decideWinner(DealerPlayersDTO gameContext) {
        Dealer dealer = gameContext.dealer();
        setDealerResultScore(dealer);
        var players = gameContext.players();
        for (int i = 0; i < players.getSize(); i++) {
            compareWithDealer(new DealerPlayerDTO(dealer, players.getPlayer(i)));
        }
    }

    private static void setDealerResultScore(Dealer dealer) {
        dealer.setResultScore(dealer.getResult());
        if (dealer.getScore().isBust()) {
            dealer.setResultScore(-1);
        }
    }

    private static void compareWithDealer(DealerPlayerDTO context) {
        Dealer dealer = context.dealer();
        Player player = context.player();
        setPlayerResultScore(player);
        if (dealer.getResultScore() == player.getResultScore()) {
            applyOutcome(context, Outcome.무, Outcome.무);
            return;
        }
        if (dealer.getResultScore() > player.getResultScore()) {
            applyOutcome(context, Outcome.승, Outcome.패);
            return;
        }
        applyOutcome(context, Outcome.패, Outcome.승);
    }

    private static void setPlayerResultScore(Player player) {
        player.setResultScore(player.getResult());
        if (player.getScore().isBust()) {
            player.setResultScore(-1);
        }
    }

    private static void applyOutcome(DealerPlayerDTO context, Outcome dealerOutcome, Outcome playerOutcome) {
        Dealer dealer = context.dealer();
        Player player = context.player();
        dealer.addResult(dealerOutcome);
        player.setOutcome(playerOutcome);
    }
}
