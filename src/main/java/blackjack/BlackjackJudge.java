package blackjack;

public class BlackjackJudge {

    public static Result judge(Cards player1, Cards player2) {
        if (player1.isBust()) {
            return Result.LOSS;
        }

        if (player1.score() > player2.score()) {
            return Result.WIN;
        }
        if (player1.score() == player2.score()) {
            return Result.DRAW;
        }
        return Result.LOSS;
    }
}
