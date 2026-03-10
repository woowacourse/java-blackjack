package blackjack.domain;

public enum GameResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    GameResult(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static GameResult calculate(final Player player, final Dealer dealer) {
        if (player.isBurst()) {
            return GameResult.LOSE;
        }
        if (dealer.isBurst() || dealer.getScore() < player.getScore()) {
            return GameResult.WIN;
        }
        if (dealer.getScore() == player.getScore()) {
            return GameResult.DRAW;
        }
        return GameResult.LOSE;
    }
}
