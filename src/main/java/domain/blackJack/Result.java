package domain.blackJack;

import static domain.blackJack.MatchResult.BLACKJACK;
import static domain.blackJack.MatchResult.DRAW;
import static domain.blackJack.MatchResult.LOSE;
import static domain.blackJack.MatchResult.WIN;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;

public class Result {
    private static final int BLACKJACK_NUMBER = 21;

    public static MatchResult calculateResultOfPlayer(Player player, Dealer dealer) {
        int playerSum = player.sum();
        int dealerSum = dealer.sum();

        if (isBlackjack(player)) {
            if (isBlackjack(dealer)) {
                return DRAW;
            }
            return BLACKJACK;
        }

        if ((!isBust(dealerSum) && dealerSum > playerSum) || isBust(playerSum)) {
            return LOSE;
        }

        if (dealerSum < playerSum || isBust(dealerSum)) {
            return WIN;
        }
        return DRAW;
    }

    private static boolean isBlackjack(Participant participant) {
        return participant.sum() == BLACKJACK_NUMBER && participant.isBlackJackCount();
    }

    public static boolean isBust(int sum) {
        return sum > BLACKJACK_NUMBER;
    }
}
