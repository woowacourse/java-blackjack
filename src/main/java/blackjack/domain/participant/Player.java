package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.MatchResult;
import blackjack.dto.PlayerGameResult;

public class Player extends Participant {

    private static final long MAX_BETTING_AMOUNT = Long.MAX_VALUE / 5 * 2;

    private boolean stopDrawing;
    private final long amount;

    public Player(String nickname, Role role, long amount) {
        super(nickname, Hand.createEmptyHands(), role);
        stopDrawing = false;
        validateAmount(amount);
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

    private void validateAmount(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0보다 큰 양수여야 합니다.");
        }
        if (amount > MAX_BETTING_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액의 최댓값을 초과한 금액입니다.");
        }
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
