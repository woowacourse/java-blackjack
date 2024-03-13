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
}
