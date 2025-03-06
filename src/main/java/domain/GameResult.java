package domain;

public enum GameResult {
    WIN, DRAW, LOSE;

    public static GameResult calculateResult(Participant dealer, Participant player) {
        final int dealerValue = dealer.getTotalValue();
        final int playerValue = player.getTotalValue();
        // 버스트 케이스
        if (dealerValue > 21 && playerValue > 21) {
            return DRAW;
        }
        if (dealerValue > 21) {
            return LOSE;
        }
        if (playerValue > 21) {
            return WIN;
        }

        // 둘 다 21 이하인 경우
        if (dealerValue == playerValue) {
            return DRAW;
        }
        if (dealerValue > playerValue) {
            return WIN;
        }
        if (dealerValue < playerValue) {
            return LOSE;
        }
        return WIN;
    }

    public GameResult reverse() {
        if (this == DRAW) {
            return DRAW;
        }
        if (this == WIN) {
            return LOSE;
        }
        return WIN;
    }
}
