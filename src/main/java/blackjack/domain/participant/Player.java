package blackjack.domain.participant;

import blackjack.domain.vo.Result;

public class Player extends Participant {
    private static final int MAXIMUM_SCORE_FOR_ADDITIONAL_CARD = 21;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isAbleToReceiveCard() {
        int minimumScore = calculateScoreWhenAceIsMinimum();
        return minimumScore < MAXIMUM_SCORE_FOR_ADDITIONAL_CARD;
    }

    public Result judgeResult(Dealer dealer) {
        int dealerScore = dealer.calculateFinalScore();
        int playerScore = calculateFinalScore();
        return Result.judge(dealerScore, playerScore);
    }
}
