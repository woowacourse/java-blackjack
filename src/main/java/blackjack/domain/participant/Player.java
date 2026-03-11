package blackjack.domain.participant;

import blackjack.domain.MatchResult;
import blackjack.domain.PlayingCards;
import blackjack.dto.PlayerGameResult;

public class Player extends Participant {

    private boolean stopDrawing;
    private long amount;

    public Player(String nickname, Role role, int amount) {
        super(nickname, PlayingCards.createEmptyHands(), role);
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
        return new PlayerGameResult(nickname, matchResult);
    }

    public MatchResult determineGameResult(Dealer dealer) {
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
