package blackjack.domain;

import blackjack.domain.card_hand.BlackjackWinDeterminable;

public enum WinningStatus {
    WIN,
    DRAW,
    LOSE,
    BLACKJACK_WIN;
    
    private static final int BUST_THRESHOLD = 21;
    private static final int BLACKJACK_SUM = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;

    public static WinningStatus determineWinningStatus(
            final BlackjackWinDeterminable myHand,
            final BlackjackWinDeterminable opponentHand
    ) {
        if (isBlackjack(myHand)) {
            return determineWinningStatusWhenBlackjack(opponentHand);
        }
        if (isBustOccurred(myHand, opponentHand)) {
            return determineWinningStatusWhenBustOccurred(myHand, opponentHand);
        }
        return determineWinningStatusWhenNormal(myHand, opponentHand);
    }
    
    private static WinningStatus determineWinningStatusWhenNormal(BlackjackWinDeterminable myHand, BlackjackWinDeterminable opponentHand) {
        if (myHand.getBlackjackSum() > opponentHand.getBlackjackSum()) {
            return WIN;
        }
        if (myHand.getBlackjackSum() == opponentHand.getBlackjackSum()) {
            return DRAW;
        }
        return LOSE;
    }
    
    private static WinningStatus determineWinningStatusWhenBlackjack(BlackjackWinDeterminable opponentHand) {
        if (isBlackjack(opponentHand)) {
            return DRAW;
        }
        return BLACKJACK_WIN;
    }
    
    private static boolean isBustOccurred(BlackjackWinDeterminable myHand, BlackjackWinDeterminable opponentHand) {
        return isBust(myHand) || isBust(opponentHand);
    }
    
    private static WinningStatus determineWinningStatusWhenBustOccurred(BlackjackWinDeterminable myHand, BlackjackWinDeterminable opponentHand) {
        if (isBust(myHand) && isBust(opponentHand)) {
            return WinningStatus.DRAW;
        }
        if (isBust(myHand)) {
            return WinningStatus.LOSE;
        }
        return WinningStatus.WIN;
    }
    
    private static boolean isBust(final BlackjackWinDeterminable cardHand) {
        return cardHand.getBlackjackSum() > BUST_THRESHOLD;
    }
    
    private static boolean isBlackjack(final BlackjackWinDeterminable cardHand) {
        return cardHand.getBlackjackSum() == BLACKJACK_SUM && cardHand.getSize() == BLACKJACK_CARD_SIZE;
    }
}
