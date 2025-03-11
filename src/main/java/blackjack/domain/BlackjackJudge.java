package blackjack.domain;

import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBlackjackCardHand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class BlackjackJudge {
    
    private final DealerBlackjackCardHand dealerHand;
    private final List<PlayerBlackjackCardHand> playerHands;
    
    public BlackjackJudge(final DealerBlackjackCardHand dealerHand, final List<PlayerBlackjackCardHand> playerHands) {
        validateNotNull(dealerHand, playerHands);
        this.dealerHand = dealerHand;
        this.playerHands = new ArrayList<>(playerHands);
    }
    
    public static BlackjackJudge from(final BlackjackGame blackjackGame) {
        return new BlackjackJudge(blackjackGame.getDealerHand(), blackjackGame.getPlayerHands());
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
    
    public Map<String, WinningStatus> getWinningStatus() {
        return playerHands.stream()
                .collect(Collectors.toMap(
                        PlayerBlackjackCardHand::getPlayerName,
                        playerHand -> WinningStatus.determineWinningStatus(playerHand, dealerHand)
                ));
    }
}
