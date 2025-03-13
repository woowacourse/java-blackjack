package game;

import java.util.List;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    BLACKJACK("블랙잭");

    private final String result;

    GameResult(String result) {
        this.result = result;
    }

    public static GameResult of(Dealer dealer, Player player) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return DRAW;
        }
        if (player.isBlackJack()) {
            return BLACKJACK;
        }
        if (dealer.isBlackJack()) {
            return LOSE;
        }
        return bust(dealer, player);
    }

    private static GameResult bust(Dealer dealer, Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return ifNotBust(dealer, player);
    }

    private static GameResult ifNotBust(Dealer dealer, Player player) {
        int totalOfPlayer = player.calculateTotalPoints();
        int totalOfDealer = dealer.calculateTotalPoints();
        if (totalOfPlayer < totalOfDealer) {
            return LOSE;
        }
        if (totalOfPlayer > totalOfDealer) {
            return WIN;
        }
        return DRAW;
    }

    public int countReversedGameResult(List<GameResult> gameResults) {
        return countByCondition(gameResults, reverse());
    }

    private int countByCondition(List<GameResult> gameResults, GameResult target) {
        return (int) gameResults.stream()
                .filter(gameResult -> gameResult == target)
                .count();
    }

    private GameResult reverse() {
        return switch (this) {
            case WIN -> LOSE;
            case LOSE -> WIN;
            case DRAW -> DRAW;
            case BLACKJACK -> BLACKJACK;
        };
    }

    public String getResult() {
        return result;
    }
}
