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

    public MatchResult calculateResultOfPlayer(Player player, Dealer dealer) {
        int dealerSum = dealer.sum();
        int playerSum = player.sum();
        if ((!isBust(dealerSum) && dealerSum > playerSum) || isBust(playerSum)) {
            return LOSE;
        }
        if (dealerSum < playerSum || isBust(dealerSum)) {
            return WIN;
        }
        if (isBlackjack(player)){
            if(isBlackjack(dealer)){
                return DRAW;
            }
            return BLACKJACK;
        }
        return DRAW;
    }

    private boolean isBlackjack(Participant participant) {
        return participant.sum()==BLACKJACK_NUMBER && participant.isBlackJackCount();
    }

    public boolean isBust(int sum) {
        return sum > BLACKJACK_NUMBER;
    }
}
