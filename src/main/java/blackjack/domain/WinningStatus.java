package blackjack.domain;

import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBlackjackCardHand;

public enum WinningStatus {
    플레이어_승리,
    무승부,
    플레이어_패배,
    ;
    
    public static WinningStatus determineWinningStatus(
            final PlayerBlackjackCardHand player,
            final DealerBlackjackCardHand dealer
    ) {
        if (player.isBust()) {
            return 플레이어_패배;
        }
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return 플레이어_승리;
        }
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return 무승부;
        }
        if (dealer.isBust()) {
            return 플레이어_승리;
        }
        if (player.getBlackjackSum() > dealer.getBlackjackSum()) {
            return 플레이어_승리;
        }
        if (player.getBlackjackSum() < dealer.getBlackjackSum()) {
            return 플레이어_패배;
        }
        return 무승부;
    }
}
