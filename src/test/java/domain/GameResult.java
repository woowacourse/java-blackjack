package domain;

import java.util.List;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String result;

    GameResult(String result) {
        this.result = result;
    }

    public static GameResult of(Dealer dealer, Player player) {
        if (player.isOverBurstBound()) {
            return GameResult.LOSE;
        }
        if (dealer.isOverBurstBound()) {
            return GameResult.WIN;
        }
        int playerTotalCardNumber = player.calculateTotalCardNumber();
        int dealerTotalCardNumber = dealer.calculateTotalCardNumber();
        if (playerTotalCardNumber < dealerTotalCardNumber) {
            return GameResult.LOSE;
        }
        if (playerTotalCardNumber > dealerTotalCardNumber) {
            return GameResult.WIN;
        }
        if (playerTotalCardNumber == 21) {
            return judgeBlackJack(dealer, player);
        }
        return GameResult.DRAW;
    }

    private static GameResult judgeBlackJack(Dealer dealer, Player player) {
        int playerCardCount = player.getCardsCount();
        int dealerCardCount = dealer.getCardsCount();
        if (playerCardCount == dealerCardCount) {
            return GameResult.DRAW;
        }
        if (playerCardCount == 2) {
            return GameResult.WIN;
        }
        if (dealerCardCount == 2) {
            return GameResult.LOSE;
        }
        throw new IllegalArgumentException("[ERROR] 승패 판정에 실패하였습니다.");
    }

    public int countGameResult(List<GameResult> gameResults) {
        if (this == GameResult.WIN) {
            return (int) gameResults.stream()
                    .filter(gameResult -> gameResult == GameResult.LOSE)
                    .count();
        }
        if (this == GameResult.LOSE) {
            return (int) gameResults.stream()
                    .filter(gameResult -> gameResult == GameResult.WIN)
                    .count();
        }
        if (this == GameResult.DRAW) {
            return (int) gameResults.stream()
                    .filter(gameResult -> gameResult == GameResult.DRAW)
                    .count();
        }
        throw new IllegalArgumentException("[ERROR] 대상 결과 개수 계산에 실패했습니다.");
    }
}
