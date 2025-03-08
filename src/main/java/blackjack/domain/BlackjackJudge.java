package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBlackjackCardHand;

public final class BlackjackJudge {
    
    private final DealerBlackjackCardHand dealerHand;
    private final List<PlayerBlackjackCardHand> playerHands;
    
    public BlackjackJudge(final DealerBlackjackCardHand dealerHand, final List<PlayerBlackjackCardHand> playerHands) {
        validateNotNull(dealerHand, playerHands);
        this.dealerHand = dealerHand;
        this.playerHands = new ArrayList<>(playerHands);
    }
    
    private void validateNotNull(final DealerBlackjackCardHand dealerHand, final List<PlayerBlackjackCardHand> playerHands) {
        if (dealerHand == null) {
            throw new IllegalArgumentException("딜러의 손패는 null이 될 수 없습니다.");
        }
        if (playerHands == null) {
            throw new IllegalArgumentException("플래이어의 손패는 null이 될 수 없습니다.");
        }
    }
    
    public int getDealerWinningCount() {
        return calculateDealerWinningStatusCountOf(WinningStatus.승리);
    }
    
    public int getDealerLosingCount() {
        return calculateDealerWinningStatusCountOf(WinningStatus.패배);
    }
    
    public int getDealerDrawingCount() {
        return calculateDealerWinningStatusCountOf(WinningStatus.무승부);
    }
    
    private int calculateDealerWinningStatusCountOf(final WinningStatus status) {
        return (int) playerHands.stream()
                .map(playerHand -> WinningStatus.determineWinningStatus(dealerHand, playerHand))
                .filter(winningStatus -> winningStatus == status)
                .count();
    }
    
    public WinningStatus getWinningStatusOf(final PlayerBlackjackCardHand playerHand) {
        if (!playerHands.contains(playerHand)) {
            throw new IllegalArgumentException("존재하지 않는 플레이어 손패입니다.");
        }
        
        return WinningStatus.determineWinningStatus(playerHand, dealerHand);
    }
}
