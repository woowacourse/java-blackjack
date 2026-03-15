package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.MatchResult;
import blackjack.dto.PlayerGameResult;

public class Player extends Participant {

    private boolean stopDrawing;
    private final long amount;

    public Player(String nickname, Role role, long amount) {
        super(nickname, Hand.createEmptyHands(), role);
        stopDrawing = false;
        this.amount = amount;
    }

    public void stop() {
        stopDrawing = true;
    }

    public boolean isDrawable() {
        if (stopDrawing) {
            return false;
        }
        return hand.isDrawable();
    }

    public PlayerGameResult determinePlayerResult(Dealer dealer) {
        MatchResult matchResult = determineGameResult(dealer);
        double payout = determinePayout(matchResult);
        return PlayerGameResult.of(nickname, matchResult, (long) payout - amount);
    }

    private double determinePayout(MatchResult matchResult) {
        if (matchResult == MatchResult.LOSE) {
            return 0;
        }
        if (matchResult == MatchResult.TIE) {
            return amount;
        }
        if (hand.isBlackJack()) {
            return amount * 2.5;
        }
        return amount * 2;
    }

    private MatchResult determineGameResult(Dealer dealer) {
        if (hand.isBusted()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBusted()) {
            return MatchResult.WIN;
        }
        if (hand.isBlackJack()) {
            return MatchResult.WIN;
        }
        return compareScore(dealer.getTotalScore(), getTotalScore());
    }

    private MatchResult compareScore(int dealerScore, int playerScore) {
        if (dealerScore == playerScore) {
            return MatchResult.TIE;
        }
        if (dealerScore > playerScore) {
            return MatchResult.LOSE;
        }
        return MatchResult.WIN;
    }
}
