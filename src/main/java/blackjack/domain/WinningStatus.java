package blackjack.domain;

import blackjack.domain.card_hand.BlackjackWinDeterminable;

public enum WinningStatus {
    승리,
    무승부,
    패배,
    ;
    
    public static WinningStatus determineWinningStatus(
            final BlackjackWinDeterminable myHand,
            final BlackjackWinDeterminable opponentHand
    ) {
        return determineWinningStatus(new BlackjackWinDetermineCondition(myHand, opponentHand));
    }
    
    private static WinningStatus determineWinningStatus(
            final BlackjackWinDetermineCondition condition
    ) {
        if (condition.hasBust()) {
            return getBustConditionResult(condition);
        }
        if (condition.isSameScore()) {
            return getSameScoreConditionResult(condition);
        }
        if (condition.isMyHandLarger()) {
            return WinningStatus.승리;
        }
        return WinningStatus.패배;
    }
    
    private static WinningStatus getBustConditionResult(
            final BlackjackWinDetermineCondition condition
    ) {
        if (condition.isMyHandOnlyBust()) {
            return WinningStatus.패배;
        }
        if (condition.isOpponentHandOnlyBust()) {
            return WinningStatus.승리;
        }
        return WinningStatus.무승부;
    }
    
    private static WinningStatus getSameScoreConditionResult(
            final BlackjackWinDetermineCondition condition
    ) {
        if (condition.isMyHandOnlyBlackjack()) {
            return WinningStatus.승리;
        }
        if (condition.isOpponentHandOnlyBlackjack()) {
            return WinningStatus.패배;
        }
        return WinningStatus.무승부;
    }
    
    private record BlackjackWinDetermineCondition(
            BlackjackWinDeterminable myHand,
            BlackjackWinDeterminable opponentHand
    ) {
        public boolean hasBust() {
            return myHand.isBust() || opponentHand.isBust();
        }
        
        public boolean isSameScore() {
            return myHand.getBlackjackSum() == opponentHand().getBlackjackSum();
        }
        
        public boolean isMyHandLarger() {
            return myHand.getBlackjackSum() > opponentHand().getBlackjackSum();
        }
        
        public boolean isMyHandOnlyBust() {
            return myHand.isBust() && !opponentHand.isBust();
        }
        
        public boolean isOpponentHandOnlyBust() {
            return !myHand.isBust() && opponentHand.isBust();
        }
        
        public boolean isMyHandOnlyBlackjack() {
            return myHand.isBlackjack() && !opponentHand.isBlackjack();
        }
        
        public boolean isOpponentHandOnlyBlackjack() {
            return !myHand.isBlackjack() && opponentHand.isBlackjack();
        }
    }
}
