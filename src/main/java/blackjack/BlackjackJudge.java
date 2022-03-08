package blackjack;

public class BlackjackJudge {

    public static boolean judge(Cards player1, Cards player2) {
        if (player1.isBust()) {
            return false;
        }

        return player1.score() > player2.score();
    }
}
