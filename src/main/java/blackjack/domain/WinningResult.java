package blackjack.domain;

public enum WinningResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String result;

    WinningResult(String result) {
        this.result = result;
    }

    public static WinningResult of(Player player, Dealer dealer) {
        if (dealer.isBurst() && player.isBurst() || player.isDraw(dealer)) {
            return DRAW;
        }
        if (dealer.isBurst() && !player.isBurst() ||
            !dealer.isBurst() && !player.isBurst() && player.isWin(dealer)) {
            return WIN;
        }
        return LOSE;
    }

    public String getResult() {
        return result;
    }


}
