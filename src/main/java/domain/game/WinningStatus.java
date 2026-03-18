package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;

import java.util.Optional;

public enum WinningStatus {
    WIN,
    TIE,
    LOSE;

    public static WinningStatus of(Player player, Dealer dealer) {
        if (isBustCase(player, dealer)) { // 버스트 승패 판단이 가능한 경우
            return bustResult(player, dealer);
        }

        if (isBlackjackCase(player, dealer)) {
            return blackjackResult(player, dealer);
        }

        return compareScore(player.score(), dealer.score());
    }

    private static boolean isBustCase(Player player, Dealer dealer) {
        return player.isBust() || dealer.isBust();
    }

    private static WinningStatus bustResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            return LOSE;
        }

        if (dealer.isBust()) {
            return WIN;
        }

        throw new IllegalStateException("버스트 상태가 올바르지 않습니다.");
    }

    private static boolean isBlackjackCase(Player player, Dealer dealer) {
        return player.isBlackjack() || dealer.isBlackjack();
    }

    private static WinningStatus blackjackResult(Player player, Dealer dealer) {
        boolean playerBlackjack = player.isBlackjack();
        boolean dealerBlackjack = dealer.isBlackjack();

        if (playerBlackjack && dealerBlackjack) {
            return TIE;
        }

        if (playerBlackjack) {
            return WIN;
        }

        if (dealerBlackjack) {
            return LOSE;
        }

        throw new IllegalStateException("블랙잭 상태가 올바르지 않습니다.");
    }

    private static WinningStatus compareScore(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return WIN;
        }

        if (playerScore < dealerScore) {
            return LOSE;
        }

        return TIE;
    }
}
