package domain.blackjack;

public enum GameResult {
    WIN, LOSE, TIE;

    GameResult changeBase() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return TIE;
    }
}
