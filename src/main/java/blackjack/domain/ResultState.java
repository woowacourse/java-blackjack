package blackjack.domain;

public enum ResultState {
    WIN("승"),
    LOSE("패");

    private final String value;

    ResultState(String value) {
        this.value = value;
    }

    public static ResultState of(Participant player, Participant dealer) {
        if (player.getState() == ScoreState.BUST) {
            return ResultState.LOSE;
        }
        if (dealer.getState() == ScoreState.BUST) {
            return ResultState.WIN;
        }
        if (player.getScore() >= dealer.getScore()) {
            return ResultState.WIN;
        }
        return ResultState.LOSE;
    }

    public boolean isWin() {
        return this == WIN;
    }

    public String getValue() {
        return value;
    }
}
