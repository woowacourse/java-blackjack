package domain.gameresult;

import domain.player.Bet;
import domain.player.Player;

public class ScoreComparator {
    private ScoreComparator() {}

    public static Bet compare(Player dealer, Player gambler) {
        if (dealer.isBust() || gambler.isBust()) {
            return calculateBettingResultOnBust(dealer, gambler);
        }

        if (gambler.isBlackJack()) {
            return calculateBettingResultOnBlackjack(dealer, gambler);
        }

        return calculateBettingResultOnScore(dealer, gambler);
    }

    private static Bet calculateBettingResultOnBlackjack(Player dealer, Player gambler) {
        if (dealer.isBlackJack()) {
            return gambler.draw();
        } // 딜러도 블랙잭

        return gambler.blackJack(); // 참가자만 블랙잭
    }

    private static Bet calculateBettingResultOnBust(Player dealer, Player gambler) {
        if (dealer.isBust()) {
            return gambler.win();
        } // 딜러가 버스트

        return gambler.lose();
        // 참가자가 버스트
    }

    private static Bet calculateBettingResultOnScore(Player dealer, Player gambler) {
        int dealerScore = dealer.getScore();
        int participantScore = gambler.getScore();

        if (dealerScore > participantScore) {
            return gambler.lose();
        }

        if (dealerScore < participantScore) {
            return gambler.win();
        }

        return gambler.draw();
    }
}
