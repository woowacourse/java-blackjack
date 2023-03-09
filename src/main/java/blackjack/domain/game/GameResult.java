package blackjack.domain.game;

public enum GameResult {

    WIN("승", 1),
    LOSE("패", -1),
    DRAW("무", 0),
    BLACK_JACK("블랙잭", 1.5);

    private final String view;
    private final double times;

    GameResult(final String view, final double times) {
        this.view = view;
        this.times = times;
    }

    public double getTimes() {
        return times;
    }

    public String getView() {
        return view;
    }
}
