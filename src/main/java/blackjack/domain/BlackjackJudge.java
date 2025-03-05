package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card_hand.DealerCardHand;
import blackjack.domain.card_hand.PlayerCardHand;

public class BlackjackJudge {
    
    private final DealerCardHand dealerCardHand;
    private final List<PlayerCardHand> playerCardHands;
    
    public BlackjackJudge(final DealerCardHand dealerCardHand, final List<PlayerCardHand> playerCardHands) {
        this.dealerCardHand = dealerCardHand;
        this.playerCardHands = new ArrayList<>(playerCardHands);
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
        for (PlayerCardHand playerCardHand : playerCardHands) {
            if (dealerCardHand.isWin(playerCardHand).equals(status)) {
                count++;
            }
        }
        return count;
    }
    
    public WinningStatus getWinningStatusOf(final PlayerCardHand playerCardHand) {
        if (!playerCardHands.contains(playerCardHand)) {
            throw new IllegalArgumentException("존재하지 않는 플레이어 손패입니다.");
        }
        
        return playerCardHand.isWin(dealerCardHand);
    }
}
