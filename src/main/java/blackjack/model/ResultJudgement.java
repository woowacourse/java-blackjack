package blackjack.model;

public class ResultJudgement {

    public BlackjackResult judge(Hand playerHand, Hand dealerHand) {
        if (playerHand.isBust()) {
            return BlackjackResult.LOSE;
        }
        if (dealerHand.isBust()) {
            return BlackjackResult.WIN;
        }

        int playerScore = playerHand.calculateScore();
        int dealerScore = dealerHand.calculateScore();
        if (playerScore > dealerScore) {
            return BlackjackResult.WIN;
        }
        if (playerScore == dealerScore) {
            return BlackjackResult.PUSH;
        }

        return BlackjackResult.LOSE;
    }
}
