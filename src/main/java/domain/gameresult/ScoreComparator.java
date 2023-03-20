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
            return BetCounter.draw();
        } // 딜러도 블랙잭

        return BetCounter.blackJack(gambler.getBet()); // 참가자만 블랙잭
    }

    private static Bet calculateBettingResultOnBust(Player dealer, Player gambler) {
        if (dealer.isBust()) {
            return BetCounter.win(gambler.getBet());
        } // 딜러가 버스트

        return BetCounter.lose(gambler.getBet());
        // 참가자가 버스트
    }

    private static Bet calculateBettingResultOnScore(Player dealer, Player gambler) {
        int dealerScore = dealer.getScore();
        int participantScore = gambler.getScore();

        if (dealerScore > participantScore) {
            return BetCounter.lose(gambler.getBet());
        }

        if (dealerScore < participantScore) {
            return BetCounter.win(gambler.getBet());
        }

        return BetCounter.draw();
    }
}
