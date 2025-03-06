package blackjack.domain;

import blackjack.domain.card_hand.BlackjackWinDeterminer;

public enum WinningStatus {
    승리,
    무승부,
    패배,
    ;
    
    private static final int BURST_THRESHOLD = 21;
    
    public static WinningStatus getWinningStatus(
            final BlackjackWinDeterminer myHand,
            final BlackjackWinDeterminer opponentHand
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
        
        if (myHand.getBlackjackSum() == opponentHand.getBlackjackSum()) {
            if (isBlackjack(myHand) && !isBlackjack(opponentHand)) {
                return WinningStatus.승리;
            }
            if (!isBlackjack(myHand) && isBlackjack(opponentHand)) {
                return WinningStatus.패배;
            }
            return WinningStatus.무승부;
        }
        
        return myHand.getBlackjackSum() > opponentHand.getBlackjackSum() ? WinningStatus.승리 : WinningStatus.패배;
    }
    
    private static boolean isBurst(final BlackjackWinDeterminer cardHand) {
        return cardHand.getBlackjackSum() > BURST_THRESHOLD;
    }
    
    private static boolean isBlackjack(final BlackjackWinDeterminer cardHand) {
        return cardHand.getBlackjackSum() == 21 && cardHand.getSize() == 2;
    }
}
