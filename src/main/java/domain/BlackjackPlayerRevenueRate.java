package domain;

import java.util.Arrays;

public enum BlackjackPlayerRevenueRate {
    // NOTE: 플레이어 입장에서 딜러에게 블랙잭으로 승리하면 수익률은 1.5배이다.
    BLACKJACK_WIN(BlackJackWinningStatus.BLACK_JACK_WIN, 1.5),
    WIN(BlackJackWinningStatus.WIN, 1),
    DRAW(BlackJackWinningStatus.DRAW, 0),
    // NOTE: 플레이어 입장에서 딜러에게 블랙잭으로 패배 하더라도 수익률은 -1.0배 이다.
    BLACKJACK_LOSE(BlackJackWinningStatus.BLACK_JACK_LOSE, -1),
    LOSE(BlackJackWinningStatus.LOSE, -1);

    private final BlackJackWinningStatus status;
    private final double rate;

    BlackjackPlayerRevenueRate(BlackJackWinningStatus status, double rate) {
        this.status = status;
        this.rate = rate;
    }

    public static double getRate(BlackJackWinningStatus status) {
        return Arrays.stream(values())
                .filter(revenueRate -> revenueRate.status == status)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 상태값입니다."))
                .rate;
    }
}
