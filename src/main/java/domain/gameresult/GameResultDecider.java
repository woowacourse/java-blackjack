package domain.gameresult;

import domain.player.Bet;
import domain.player.Dealer;
import domain.player.Player;

public class GameResultDecider {
    private GameResultDecider() {}

    public static Bet decideGameResult(Dealer dealer, Player participant) {
        if (dealer.isBust() || participant.isBust()) {
            return calculateBettingResultOnBust(dealer, participant);
        }

        if (participant.isBlackJack()) {
            return calculateBettingResultOnBlackjack(dealer, participant);
        }

        return calculateBettingResultOnScore(dealer, participant);
    }

    private static Bet calculateBettingResultOnBlackjack(Dealer dealer, Player participant) {
        if (dealer.isBlackJack()) {
            return participant.draw();
        } // 딜러도 블랙잭
        return participant.blackJack(); // 참가자만 블랙잭
    }

    private static Bet calculateBettingResultOnBust(Dealer dealer, Player participant) {
        if (dealer.isBust()) {
            return participant.win();
        } // 딜러가 버스트

        return participant.lose();
        // 참가자가 버스트
    }

    private static Bet calculateBettingResultOnScore(Dealer dealer, Player participant) {
        int dealerScore = dealer.getScore();
        int participantScore = participant.getScore();

        if (dealerScore > participantScore) {
            return participant.lose();
        }

        if (dealerScore < participantScore) {
            return participant.win();
        }

        return participant.draw();
    }
}
