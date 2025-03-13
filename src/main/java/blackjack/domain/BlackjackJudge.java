package blackjack.domain;

import blackjack.domain.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBlackjackCardHand;

import java.util.Map;

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
    
    public int getDealerBlackjackWinningCount() {
        return getCountOf(WinningStatus.BLACKJACK_WIN);
    }
    
    public int getDealerWinningCount() {
        return getCountOf(WinningStatus.WIN);
    }
    
    public int getDealerLosingCount() {
        return getCountOf(WinningStatus.LOSE);
    }
    
    public int getDealerDrawingCount() {
        return getCountOf(WinningStatus.DRAW);
    }
    
    private int getCountOf(final WinningStatus status) {
        return (int) playerBlackjackCardHands.stream()
                .filter(player -> WinningStatus.determineWinningStatus(dealerBlackjackCardHand, player) == status)
                .count();
    }
    
    public WinningStatus getWinningStatusOf(final PlayerBlackjackCardHand playerBlackjackCardHand) {
        if (!playerBlackjackCardHands.contains(playerBlackjackCardHand)) {
            throw new IllegalArgumentException("존재하지 않는 플레이어 손패입니다.");
        }
        
        return WinningStatus.determineWinningStatus(playerBlackjackCardHand, dealerBlackjackCardHand);
    }
    
    public Map<Player, WinningStatus> getWinningStatusOfAllPlayers() {
        Map<Player, WinningStatus> playersWinningStatus = new HashMap<>();
        for (PlayerBlackjackCardHand playerBlackjackCardHand : playerBlackjackCardHands) {
            playersWinningStatus.put(playerBlackjackCardHand.getPlayer(),
                    getWinningStatusOf(playerBlackjackCardHand));
        }
        return playersWinningStatus;
    }
}
