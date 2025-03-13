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
        if (isBustOccurred(myHand, opponentHand)) {
            return determineWinningStatusWhenBustOccurred(myHand, opponentHand);
        }
        
        if (isSumEquals(myHand, opponentHand)) {
            return determineWinningStatusWhenSumEquals(myHand, opponentHand);
        }
        
        if (isMyHandGreater(myHand, opponentHand)) {
            return WinningStatus.WIN;
        }
        return WinningStatus.LOSE;
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
    
    private static boolean isSumEquals(BlackjackWinDeterminable myHand, BlackjackWinDeterminable opponentHand) {
        return myHand.getBlackjackSum() == opponentHand.getBlackjackSum();
    }
    
    private static WinningStatus determineWinningStatusWhenSumEquals(BlackjackWinDeterminable myHand, BlackjackWinDeterminable opponentHand) {
        if (isBlackjack(myHand) && !isBlackjack(opponentHand)) {
            return WinningStatus.BLACKJACK_WIN;
        }
        if (!isBlackjack(myHand) && isBlackjack(opponentHand)) {
            return WinningStatus.LOSE;
        }
        return WinningStatus.DRAW;
    }
    
    private static boolean isMyHandGreater(BlackjackWinDeterminable myHand, BlackjackWinDeterminable opponentHand) {
        return myHand.getBlackjackSum() > opponentHand.getBlackjackSum();
    }
    
    private static boolean isBust(final BlackjackWinDeterminable cardHand) {
        return cardHand.getBlackjackSum() > BUST_THRESHOLD;
    }
    
    private static boolean isBlackjack(final BlackjackWinDeterminable cardHand) {
        return cardHand.getBlackjackSum() == BLACKJACK_SUM && cardHand.getSize() == BLACKJACK_CARD_SIZE;
    }
}
