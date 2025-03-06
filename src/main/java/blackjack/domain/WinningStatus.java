package blackjack.domain;

import blackjack.domain.card_hand.BlackjackCardHand;

public enum WinningStatus {
    승리,
    무승부,
    패배,
    ;
    
    private static final int BURST_THRESHOLD = 21;
    
    public static WinningStatus getWinningStatus(
            final BlackjackCardHand myHand,
            final BlackjackCardHand opponentHand
    ) {
        if (isBurst(myHand) || isBurst(opponentHand)) {
            if (isBurst(myHand) && isBurst(opponentHand)) {
                return WinningStatus.무승부;
            }
            if (isBurst(myHand)) {
                return WinningStatus.패배;
            }
            return WinningStatus.승리;
        }
        
        if (myHand.getSum() == opponentHand.getSum()) {
            if (isBlackjack(myHand) && !isBlackjack(opponentHand)) {
                return WinningStatus.승리;
            }
            if (!isBlackjack(myHand) && isBlackjack(opponentHand)) {
                return WinningStatus.패배;
            }
            return WinningStatus.무승부;
        }
        
        return myHand.getSum() > opponentHand.getSum() ? WinningStatus.승리 : WinningStatus.패배;
    }
    
    private static boolean isBurst(final BlackjackCardHand cardHand) {
        return cardHand.getSum() > BURST_THRESHOLD;
    }
    
    private static boolean isBlackjack(final BlackjackCardHand cardHand) {
        return cardHand.getSum() == 21 && cardHand.getSize() == 2;
    }
}
