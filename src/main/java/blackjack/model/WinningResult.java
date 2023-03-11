package blackjack.model;

import java.util.List;

public class WinningResult {
    private final double betting;
    private final int win;
    private final int draw;
    private final int lose;

    public WinningResult(int win, int draw, int lose, double betting) {
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.betting = betting;
    }

    public WinningResult() {
        this(0, 0, 0, 0);
    }

    public WinningResult win(int betting, boolean isBlackjack) {
        if (isBlackjack) {
            return new WinningResult(win + 1, draw, lose, betting * 1.5);
        }
        return new WinningResult(win + 1, draw, lose, betting);
    }

    public WinningResult draw(int betting) {
        return new WinningResult(win, draw + 1, lose, 0);
    }

    public WinningResult lose(int betting) {
        return new WinningResult(win, draw, lose + 1, -betting);
    }

    public WinningResult merge(WinningResult other) {
        return new WinningResult(win + other.win, draw + other.draw, lose + other.lose, betting + other.betting);
    }

    public int getWin() {
        return win;
    }

    public int getDraw() {
        return draw;
    }

    public int getLose() {
        return lose;
    }

    public double getBetting() {
        return betting;
    }
}
