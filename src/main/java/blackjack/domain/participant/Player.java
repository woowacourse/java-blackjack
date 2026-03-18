package blackjack.domain.participant;

import blackjack.domain.Amount;
import blackjack.domain.Hand;
import blackjack.domain.MatchResult;
import blackjack.domain.Nickname;
import blackjack.dto.PlayerGameResult;

public class Player extends Participant {

    private boolean stopDrawing;
    private final Amount amount;

    public Player(Nickname nickname, Role role, Amount amount) {
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
        long profit = Math.round(payout - amount.getValue());
        return PlayerGameResult.of(nickname.getValue(), matchResult, profit);
    }

    private double determinePayout(MatchResult matchResult) {
        if (matchResult == MatchResult.LOSE) {
            return 0;
        }
        if (matchResult == MatchResult.TIE) {
            return amount.getValue();
        }
        if (hand.isBlackJack()) {
            return amount.getValue() * 2.5;
        }
        return amount.getValue() * 2;
    }

    private MatchResult determineGameResult(Dealer dealer) {
        if (hand.isBusted()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBusted()) {
            return MatchResult.WIN;
        }
        if (hand.isBlackJack() && !dealer.isBlackJack()) {
            return MatchResult.WIN;
        }
        if (!hand.isBlackJack() && dealer.isBlackJack()) {
            return MatchResult.LOSE;
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
