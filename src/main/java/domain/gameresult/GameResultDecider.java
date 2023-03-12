package domain.gameresult;

import domain.player.Dealer;
import domain.player.Player;

public class GameResultDecider {
    private GameResultDecider() {}

    public static void decideGameResult(Dealer dealer, Player participant, GameResult gameResult) {
        if (dealer.isBust() || participant.isBust()) {
            decideGameResultOnBurst(dealer, participant, gameResult);
            return;
        }
        decideGameResultOnScore(dealer, participant, gameResult);
    }

    private static void decideGameResultOnBurst(Dealer dealer, Player participant, GameResult gameResult) {
        if (dealer.isBust() && participant.isBust()) {
            gameResult.addDrawCount(participant);
            return;
        } // 둘 다 버스트

        if (dealer.isBust()) {
            gameResult.addParticipantWinningCase(participant);
            return;
        } // 딜러가 버스트

        gameResult.addParticipantLosingCase(participant);
        // 참가자가 버스트
    }

    private static void decideGameResultOnScore(Dealer dealer, Player participant, GameResult gameResult) {
        int dealerScore = dealer.getScore();
        int participantScore = participant.getScore();

        if (dealerScore > participantScore) {
            gameResult.addParticipantLosingCase(participant);
            return;
        }

        if (dealerScore < participantScore) {
            gameResult.addParticipantWinningCase(participant);
            return;
        }

        gameResult.addDrawCount(participant);
    }
}
