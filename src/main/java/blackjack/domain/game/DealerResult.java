package blackjack.domain.game;

public class DealerResult {

    private final int win;
    private final int lose;
    private final int draw;

    public DealerResult(int win, int lose, int draw) {
        this.win = win;
        this.draw = draw;
        this.lose = lose;
    }
}
