package blackjack.model;

import java.util.List;

public class WinningResult {
    private final int win;
    private final int draw;
    private final int lose;

    public WinningResult(int win, int draw, int lose) {
        this.win = win;
        this.draw = draw;
        this.lose = lose;
    }

    public WinningResult() {
        this.win = 0;
        this.draw = 0;
        this.lose = 0;
    }

    public WinningResult win() {
        return new WinningResult(win + 1, draw, lose);
    }

    public WinningResult draw() {
        return new WinningResult(win, draw + 1, lose);
    }

    public WinningResult lose() {
        return new WinningResult(win, draw, lose + 1);
    }

    public WinningResult merge(WinningResult other) {
        return new WinningResult(win + other.win, draw + other.draw, lose + other.lose);
    }

    public List<Integer> getResult() {
        return List.of(win, draw, lose);
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
}
