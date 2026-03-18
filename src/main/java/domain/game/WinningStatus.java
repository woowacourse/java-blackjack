package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;

public enum WinningStatus {
    WIN,
    TIE,
    LOSE;

    public static WinningStatus of(Player player, Dealer dealer) {
        if (isBustCase(player, dealer)) {
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

        throw new IllegalStateException("버스트 판정 분기에 진입했지만, 플레이어와 딜러 모두 버스트 상태가 아닙니다.");
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

        throw new IllegalStateException("블랙잭 판정 분기에 진입했지만, 플레이어와 딜러 모두 블랙잭이 아닙니다.");
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
