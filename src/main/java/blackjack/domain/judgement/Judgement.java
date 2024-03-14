package blackjack.domain.judgement;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class Judgement {

    public JudgementResult judgePlayer(Dealer dealer, Player player) {
        int playerScore = player.calculateScore();
        int dealerScore = dealer.calculateScore();

        if (player.isBust()) {
            return JudgementResult.LOSE;
        }

        if (dealer.isBust() || playerScore > dealerScore) {
            return JudgementResult.WIN;
        }

        if (player.isBlackJack() && dealer.isBlackJack()) {
            return JudgementResult.TIE;
        }

        if (player.isBlackJack()) {
            return JudgementResult.BLACKJACK_WIN;
        }

        if (dealer.isBlackJack() || playerScore < dealerScore) {
            return JudgementResult.LOSE;
        }

        return JudgementResult.TIE;
    }
}
