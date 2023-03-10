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
        if (dealer.getState()
                  .isBlackjack()) {
            return getResultDealerBlackjack(player);
        }
        if (dealer.getState()
                  .isBust()) {
            return getResultDealerBust(player);
        }
        return getResultDealerOther(player, dealer);
    }

    private static ResultState getResultDealerBlackjack(Player player) {
        if (player.getState()
                  .isBlackjack()) {
            return ResultState.DRAW;
        }
        return ResultState.LOSE;
    }

    private static ResultState getResultDealerBust(Player player) {
        if (player.getState()
                  .isBust()) {
            return ResultState.LOSE;
        }
        return ResultState.WIN;
    }

    private static ResultState getResultDealerOther(Player player, Dealer dealer) {
        if (player.getState().isBlackjack() || (player.canHit() && player.getScore() > dealer.getScore())) {
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
