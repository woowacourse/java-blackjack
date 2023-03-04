package blackjack.domain;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.player.User;
import blackjack.domain.result.Result;

import java.util.HashMap;

public class BlackjackGame {

    public static void giveFirstCards(Players players, Dealer dealer) {
        Cards.init();
        for (Player player : players.getPlayers()) {
            giveFirstCard(player);
        }
        giveFirstCard(dealer);
    }

    private static void giveFirstCard(User user) {
        for (int cardIndex = 0; cardIndex < 2; cardIndex++) {
            user.updateCardScore(Cards.giveFirstCard());
        }
    }

    public static void calculateResults(
            int dealerScore,
            Players players,
            HashMap<Player, Result> playerResults,
            HashMap<Result, Integer> dealerResults
    ) {
        for (Player player : players.getPlayers()) {
            Result playerResult = Result.calculateResult(player.getTotalScore(), dealerScore);
            Result dealerResult = playerResult.ofOppositeResult();
            playerResults.put(player, playerResult);
            dealerResults.put(dealerResult, dealerResults.get(dealerResult) + 1);
        }
    }

}
