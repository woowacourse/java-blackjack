package blackjack.model.bettings;

import java.util.Optional;
import blackjack.model.cards.Hand;

public enum Result {
    BLACKJACK(1.5),
    WIN(1),
    TIE(0),
    LOSE(-1);

    private static final int BLACKJACK_SCORE = 21;
    private static final int BUST_SCORE_THRESHOLD = 21;
    private static final int CARD_COUNT_THRESHOLD = 2;

    private final double rate;

    Result(double rate) {
        this.rate = rate;
    }

    public static Result evaluateResult(boolean isDealer, Hand myHand, Hand oppenentHand) {
        return Optional.ofNullable(checkBlackjack(myHand, oppenentHand))
                .orElseGet(() -> Optional.ofNullable(checkDealerBust(isDealer, myHand, oppenentHand))
                        .orElseGet(() -> Optional.ofNullable(checkPlayerBust(isDealer, myHand, oppenentHand))
                                .orElseGet(() -> checkScore(myHand, oppenentHand))));
    }

    private static Result checkBlackjack(Hand myHand, Hand opponentHand) {
        if (isBlackJack(myHand) && isBlackJack(opponentHand)) {
            return TIE;
        }
        if (isBlackJack(myHand) && !isBlackJack(opponentHand)) {
            return BLACKJACK;
        }
        if (!isBlackJack(myHand) && isBlackJack(opponentHand)) {
            return LOSE;
        }
        return null;
    }

    private static boolean isBlackJack(Hand hand) {
        return hand.getScore() == BLACKJACK_SCORE && hand.getHandSize() == CARD_COUNT_THRESHOLD;
    }

    private static Result checkDealerBust(boolean isDealer, Hand myHand, Hand opponentHand) {
        if (!isDealer) {
            return null;
        }
        if (opponentHand.getScore() > BUST_SCORE_THRESHOLD) {
            return WIN;
        }
        if (myHand.getScore() > BUST_SCORE_THRESHOLD) {
            return LOSE;
        }
        return null;
    }

    private static Result checkPlayerBust(boolean isDealer, Hand myHand, Hand opponentHand) {
        if (isDealer) {
            return null;
        }
        if (myHand.getScore() > BUST_SCORE_THRESHOLD) {
            return LOSE;
        }
        if (opponentHand.getScore() > BUST_SCORE_THRESHOLD) {
            return WIN;
        }
        return null;
    }

    private static Result checkScore(Hand myHand, Hand opponentHand) {
        if (myHand.getScore() > opponentHand.getScore()) {
            return WIN;
        }
        if (myHand.getScore() < opponentHand.getScore()) {
            return LOSE;
        }
        return TIE;
    }

    public double getRate() {
        return rate;
    }
}
