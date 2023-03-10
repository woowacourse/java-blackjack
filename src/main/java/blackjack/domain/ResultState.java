package blackjack.domain;

public enum ResultState {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String value;

    ResultState(String value) {
        this.value = value;
    }

    public static ResultState of(Player player, Dealer dealer) {
        if (player.getState() == ScoreState.BUST) {
            return ResultState.LOSE;
        }
        if (dealer.getState() == ScoreState.BUST) {
            return ResultState.WIN;
        }
        if (player.getScore() > dealer.getScore()) {
            return ResultState.WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return ResultState.DRAW;
        }
        return ResultState.LOSE;
    }

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isLose() {
        return this == LOSE;
    }

    public boolean isDraw() {
        return this == DRAW;
    }

    public String getValue() {
        return value;
    }
}
