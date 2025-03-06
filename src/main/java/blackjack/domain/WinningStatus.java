package blackjack.domain;

import blackjack.domain.card_hand.BlackjackWinDeterminable;

public enum WinningStatus {
    승리,
    무승부,
    패배,
    ;
    
    private static final int BUST_THRESHOLD = 21;
    
    public static WinningStatus determineWinningStatus(
            final BlackjackWinDeterminable myHand,
            final BlackjackWinDeterminable opponentHand
    ) {
        if (isBust(myHand) || isBust(opponentHand)) {
            if (isBust(myHand) && isBust(opponentHand)) {
                return WinningStatus.무승부;
            }
            if (isBust(myHand)) {
                return WinningStatus.패배;
            }
            return WinningStatus.승리;
        }
        
        if (myHand.getBlackjackSum() == opponentHand.getBlackjackSum()) {
            if (isBlackjack(myHand) && !isBlackjack(opponentHand)) {
                return WinningStatus.승리;
            }
            if (!isBlackjack(myHand) && isBlackjack(opponentHand)) {
                return WinningStatus.패배;
            }
            return WinningStatus.무승부;
        }
        
        if (myHand.getBlackjackSum() > opponentHand.getBlackjackSum()) {
            return WinningStatus.승리;
        }
        return WinningStatus.패배;
    }
    
    private static boolean isBust(final BlackjackWinDeterminable cardHand) {
        return cardHand.getBlackjackSum() > BUST_THRESHOLD;
    }
    
    private static boolean isBlackjack(final BlackjackWinDeterminable cardHand) {
        return cardHand.getBlackjackSum() == 21 && cardHand.getSize() == 2;
    }
}
