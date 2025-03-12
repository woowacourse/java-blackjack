package domain;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String title;

    GameResult(String title) {
        this.title = title;
    }

    public static GameResult from(Player player, Dealer dealer) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        if (dealer.isBlackJack() && player.isBlackJack()) {
            return DRAW;
        }
        if (dealer.isBlackJack()) {
            return LOSE;
        }
        if (player.isBlackJack()) {
            return WIN;
        }

        return getResultByTotalScore(player, dealer);
    }

    private static GameResult getResultByTotalScore(Player player, Dealer dealer) {
        if (dealer.getTotalScore() > player.getTotalScore()) {
            return LOSE;
        }
        if (dealer.getTotalScore() == player.getTotalScore()) {
            return DRAW;
        }
        return WIN;
    }

    public String getTitle() {
        return title;
    }
}
