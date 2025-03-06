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
        if (checkBurstCase(myHand, opponentHand)) {
            return getBurstResult(myHand, opponentHand);
        }
        
        if (myHand.getSum() == opponentHand.getSum()) {
            if (myHand.getSum() == 21) {
                if (isBlackjack(myHand) != isBlackjack(opponentHand)) {
                    if (isBlackjack(myHand)) {
                        return WinningStatus.승리;
                    }
                    return WinningStatus.패배;
                }
                return WinningStatus.무승부;
            }
            return WinningStatus.무승부;
        }
        
        return myHand.getSum() > opponentHand.getSum() ? WinningStatus.승리 : WinningStatus.패배;
    }
    
    private static boolean checkBurstCase(
            final BlackjackCardHand dealerCardHand,
            final BlackjackCardHand playerCardHand
    ) {
        return isBurst(dealerCardHand) || isBurst(playerCardHand);
    }
    
    private static WinningStatus getBurstResult(
            final BlackjackCardHand dealerCardHand,
            final BlackjackCardHand playerCardHand
    ) {
        if (isBurst(dealerCardHand) && isBurst(playerCardHand)) {
            return WinningStatus.무승부;
        }
        
        if (isBurst(dealerCardHand)) {
            return WinningStatus.패배;
        }
        
        return WinningStatus.승리;
    }
    
    private static boolean isBurst(final BlackjackCardHand cardHand) {
        return cardHand.getSum() > BURST_THRESHOLD;
    }
    
    private static boolean isBlackjack(final BlackjackCardHand cardHand) {
        return cardHand.getSum() == 21 && cardHand.getSize() == 2;
    }
}
