package domain.player;

import domain.Bet;

public enum DealerStatus {
    //TODO: blackjackWin 1.5배 반환 추가, 둘다 버스트면 딜러 승(플레이어 돈 잃음), 비기면 결과 0원
    WIN(1),
    DRAW(0),
    LOSE(-1),
    BLACKJACK_LOSE(-1.5);

    private final double multiply;

    DealerStatus(final double multiply) {
        this.multiply = multiply;
    }

    public double calculatePrize(Bet bet) {
        return (double) bet.getValue() * multiply;
    }
}
