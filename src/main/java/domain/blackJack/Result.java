package domain.blackJack;

import static domain.blackJack.MatchResult.BLACKJACK;
import static domain.blackJack.MatchResult.DRAW;
import static domain.blackJack.MatchResult.LOSE;
import static domain.blackJack.MatchResult.WIN;

import domain.participant.Dealer;
import domain.participant.Player;

public class Result {

    public static MatchResult calculateResultOfPlayer(Player player, Dealer dealer) {
        int playerSum = player.sum();
        int dealerSum = dealer.sum();

        if (player.isBlackjack()) {
            if (dealer.isBlackjack()) {
                return DRAW;
            }
            return BLACKJACK;
        }

        if ((!dealer.isBust() && dealerSum > playerSum) || player.isBust()) {
            return LOSE;
        }

        if (dealerSum < playerSum || dealer.isBust()) {
            return WIN;
        }
        return DRAW;
    }
}
