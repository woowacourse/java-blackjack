package domain.blackjack;

public enum GameResult {
    WIN, LOSE, TIE, WIN_BLACK_JACK;

    GameResult changeBase() {
        if (this == WIN || this == WIN_BLACK_JACK) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return TIE;
    }

    //todo 메서드 이름 변경
    int calculateMoney(int bettingMoney) {
        if (this == WIN) {
            return bettingMoney;
        }
        if (this == WIN_BLACK_JACK) {
            return (int) (bettingMoney * 1.5);
        }
        if (this == TIE) {
            return 0;
        }
        return -bettingMoney;
    }
}
