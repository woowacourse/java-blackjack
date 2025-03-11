package blackjack.domain.card_hand;

import blackjack.domain.WinningStatus;
import blackjack.domain.deck.BlackjackCardHandInitializer;
import blackjack.domain.player.Player;

public final class PlayerBettingBlackjackCardHand {
    
    private static final double BLACK_JACK_PROFIT_RATIO = 1.5;
    
    private final PlayerBlackjackCardHand hand;
    private final int bettingAmount;
    
    private PlayerBettingBlackjackCardHand(final PlayerBlackjackCardHand hand, final int bettingAmount) {
        this.hand = hand;
        this.bettingAmount = bettingAmount;
    }
    
    public static PlayerBettingBlackjackCardHand from(
            final Player player,
            final int bettingAmount,
            final BlackjackCardHandInitializer initializer
    ) {
        return new PlayerBettingBlackjackCardHand(new PlayerBlackjackCardHand(player, initializer), bettingAmount);
    }
    
    public double compareHand(final DealerBlackjackCardHand dealerHand) {
        final WinningStatus winningStatus = WinningStatus.determineWinningStatus(hand, dealerHand);
        if (isBlackjackWinning(winningStatus)) {
            return bettingAmount * BLACK_JACK_PROFIT_RATIO;
        }
        return switch (winningStatus) {
            case 플레이어_승리 -> bettingAmount;
            case 무승부 -> 0;
            case 플레이어_패배 -> -bettingAmount;
        };
    }
    
    private boolean isBlackjackWinning(final WinningStatus winningStatus) {
        return winningStatus == WinningStatus.플레이어_승리 && hand.isBlackjack();
    }
}
