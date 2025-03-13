package game;

import java.util.List;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private static final int BLACK_JACK_CONDITION_COUNT = 2;

    private final String result;

    GameResult(String result) {
        this.result = result;
    }

    public static GameResult of(Dealer dealer, Player player) {
        if (player.isOverBustBound()) {
            return GameResult.LOSE;
        }
        if (dealer.isOverBustBound()) {
            return GameResult.WIN;
        }
        return judgeGameResultIfNotBust(dealer, player);
    }

    private static GameResult judgeGameResultIfNotBust(Dealer dealer, Player player) {
        int playerTotalCardNumber = player.calculateTotalCardNumber();
        int dealerTotalCardNumber = dealer.calculateTotalCardNumber();
        if (playerTotalCardNumber < dealerTotalCardNumber) {
            return GameResult.LOSE;
        }
        if (playerTotalCardNumber > dealerTotalCardNumber) {
            return GameResult.WIN;
        }
        if (playerTotalCardNumber == Hand.BUST_BOUND) {
            return judgeGameResultIfBlackJack(dealer, player);
        }
        return GameResult.DRAW;
    }

    private static GameResult judgeGameResultIfBlackJack(Dealer dealer, Player player) {
        int playerCardCount = player.getCardsCount();
        int dealerCardCount = dealer.getCardsCount();
        if (playerCardCount == dealerCardCount) {
            return GameResult.DRAW;
        }
        if (playerCardCount == BLACK_JACK_CONDITION_COUNT) {
            return GameResult.WIN;
        }
        if (dealerCardCount == BLACK_JACK_CONDITION_COUNT) {
            return GameResult.LOSE;
        }
        throw new IllegalArgumentException("[ERROR] 승패 판정에 실패하였습니다.");
    }

    public int countReversedGameResult(List<GameResult> gameResults) {
        return countByCondition(gameResults, getReversedGameResult());
    }

    private GameResult getReversedGameResult() {
        if (this == GameResult.WIN) {
            return GameResult.LOSE;
        }
        if (this == GameResult.LOSE) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    private int countByCondition(List<GameResult> gameResults, GameResult target) {
        return (int) gameResults.stream()
                .filter(gameResult -> gameResult == target)
                .count();
    }

    public String getResult() {
        return result;
    }
}
