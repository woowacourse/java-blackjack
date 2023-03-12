package domain.gameresult;

import domain.player.Dealer;
import domain.player.Player;

public class GameResultDecider {
    private GameResultDecider() {}

    public static void decideGameResult(Dealer dealer, Player participant) {
        if (dealer.isBust() || participant.isBust()) {
            decideGameResultOnBurst(dealer, participant);
            return;
        }
        decideGameResultOnScore(dealer, participant);
    }

    private static void decideGameResultOnBurst(Dealer dealer, Player participant) {
        if (dealer.isBust() && participant.isBust()) {
            return;
        } // 둘 다 버스트

        if (dealer.isBust()) {
            participant.profit();
            return;
        } // 딜러가 버스트

        participant.lose();
        // 참가자가 버스트
    }

    private static void decideGameResultOnScore(Dealer dealer, Player participant) {
        int dealerScore = dealer.getScore();
        int participantScore = participant.getScore();

        if (dealerScore > participantScore) {
            participant.lose();
            return;
        }

        if (dealerScore < participantScore) {
            participant.profit();
        }
    }
}
