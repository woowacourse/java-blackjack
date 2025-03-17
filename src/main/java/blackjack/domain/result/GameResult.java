package blackjack.domain.result;

public record GameResult(
        GameResultType gameResultType,
        boolean isBlackJack
) {
    public boolean isWinByBlackJack() {
        return isBlackJack && gameResultType.equals(GameResultType.WIN);
    }

    public boolean isWinByNotBlackJack() {
        return !isBlackJack && gameResultType.equals(GameResultType.WIN);
    }

    public boolean isTie() {
        return gameResultType.equals(GameResultType.TIE);
    }

    public boolean isLose() {
        return gameResultType.equals(GameResultType.LOSE);
    }
}
