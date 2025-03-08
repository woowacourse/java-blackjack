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
        if (isBustCondition(myHand, opponentHand)) {
            return getBustConditionResult(myHand, opponentHand);
        }
        if (isSameScoreCondition(myHand, opponentHand)) {
            return getSameScoreConditionResult(myHand, opponentHand);
        }
        if (myHand.getBlackjackSum() > opponentHand.getBlackjackSum()) {
            return WinningStatus.승리;
        }
        return WinningStatus.패배;
    }
    
    private static boolean isBustCondition(
            final BlackjackWinDeterminable myHand,
            final BlackjackWinDeterminable opponentHand
    ) {
        return isBust(myHand) || isBust(opponentHand);
    }
    
    private static WinningStatus getBustConditionResult(
            final BlackjackWinDeterminable myHand,
            final BlackjackWinDeterminable opponentHand
    ) {
        final boolean isMyHandBust = isBust(myHand);
        final boolean isOpponentHandBust = isBust(opponentHand);
        
        if (isMyHandBust && isOpponentHandBust) {
            return WinningStatus.무승부;
        }
        if (isMyHandBust) {
            return WinningStatus.패배;
        }
        if (isOpponentHandBust) {
            return WinningStatus.승리;
        }
        return WinningStatus.무승부;
    }
    
    private static boolean isSameScoreCondition(
            final BlackjackWinDeterminable myHand,
            final BlackjackWinDeterminable opponentHand
    ) {
        return myHand.getBlackjackSum() == opponentHand.getBlackjackSum();
    }
    
    private static WinningStatus getSameScoreConditionResult(
            final BlackjackWinDeterminable myHand,
            final BlackjackWinDeterminable opponentHand
    ) {
        final boolean isMyHandBlackjack = isBlackjack(myHand);
        final boolean isOpponentHandBlackjack = isBlackjack(opponentHand);
        
        if (isMyHandBlackjack && !isOpponentHandBlackjack) {
            return WinningStatus.승리;
        }
        if (!isMyHandBlackjack && isOpponentHandBlackjack) {
            return WinningStatus.패배;
        }
        return WinningStatus.무승부;
    }
    
    private static boolean isBust(final BlackjackWinDeterminable cardHand) {
        return cardHand.getBlackjackSum() > BUST_THRESHOLD;
    }
    
    private static boolean isBlackjack(final BlackjackWinDeterminable cardHand) {
        return cardHand.getBlackjackSum() == 21 && cardHand.getSize() == 2;
    }
}
