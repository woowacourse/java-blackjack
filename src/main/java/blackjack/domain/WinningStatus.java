package blackjack.domain;

import blackjack.domain.card_hand.BlackjackWinDeterminable;

public enum WinningStatus {
    승리,
    무승부,
    패배,
    ;
    
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
            return WinningStatus.승리;
        }
        return WinningStatus.패배;
    }
    
    private static boolean isBustOccurred(BlackjackWinDeterminable myHand, BlackjackWinDeterminable opponentHand) {
        return isBust(myHand) || isBust(opponentHand);
    }
    
    private static WinningStatus determineWinningStatusWhenBustOccurred(BlackjackWinDeterminable myHand, BlackjackWinDeterminable opponentHand) {
        if (isBust(myHand) && isBust(opponentHand)) {
            return WinningStatus.무승부;
        }
        if (isBust(myHand)) {
            return WinningStatus.패배;
        }
        return WinningStatus.승리;
    }
    
    private static boolean isSumEquals(BlackjackWinDeterminable myHand, BlackjackWinDeterminable opponentHand) {
        return myHand.getBlackjackSum() == opponentHand.getBlackjackSum();
    }
    
    private static WinningStatus determineWinningStatusWhenSumEquals(BlackjackWinDeterminable myHand, BlackjackWinDeterminable opponentHand) {
        if (isBlackjack(myHand) && !isBlackjack(opponentHand)) {
            return WinningStatus.승리;
        }
        if (!isBlackjack(myHand) && isBlackjack(opponentHand)) {
            return WinningStatus.패배;
        }
        return WinningStatus.무승부;
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
