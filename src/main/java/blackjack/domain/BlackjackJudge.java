package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBlackjackCardHand;

public final class BlackjackJudge {
    
    private final DealerBlackjackCardHand dealerBlackjackCardHand;
    private final List<PlayerBlackjackCardHand> playerBlackjackCardHands;
    
    public BlackjackJudge(final DealerBlackjackCardHand dealerBlackjackCardHand, final List<PlayerBlackjackCardHand> playerBlackjackCardHands) {
        validateNotNull(dealerBlackjackCardHand, playerBlackjackCardHands);
        this.dealerBlackjackCardHand = dealerBlackjackCardHand;
        this.playerBlackjackCardHands = new ArrayList<>(playerBlackjackCardHands);
    }
    
    private void validateNotNull(final DealerBlackjackCardHand dealerBlackjackCardHand, final List<PlayerBlackjackCardHand> playerBlackjackCardHands) {
        if (dealerBlackjackCardHand == null) {
            throw new IllegalArgumentException("딜러의 손패는 null이 될 수 없습니다.");
        }
        if (playerBlackjackCardHands == null) {
            throw new IllegalArgumentException("플래이어의 손패는 null이 될 수 없습니다.");
        }
    }
    
    public int getDealerWinningCount() {
        return getCountOf(WinningStatus.승리);
    }
    
    public int getDealerLosingCount() {
        return getCountOf(WinningStatus.패배);
    }
    
    public int getDealerDrawingCount() {
        return getCountOf(WinningStatus.무승부);
    }
    
    private int getCountOf(final WinningStatus status) {
        int count = 0;
        for (PlayerBlackjackCardHand playerBlackjackCardHand : playerBlackjackCardHands) {
            if (WinningStatus.determineWinningStatus(dealerBlackjackCardHand, playerBlackjackCardHand) == status) {
                count++;
            }
        }
        return count;
    }
    
    public WinningStatus getWinningStatusOf(final PlayerBlackjackCardHand playerBlackjackCardHand) {
        if (!playerBlackjackCardHands.contains(playerBlackjackCardHand)) {
            throw new IllegalArgumentException("존재하지 않는 플레이어 손패입니다.");
        }
        
        return WinningStatus.determineWinningStatus(playerBlackjackCardHand, dealerBlackjackCardHand);
    }
}
