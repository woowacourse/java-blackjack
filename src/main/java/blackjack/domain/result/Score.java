package blackjack.domain.result;

import blackjack.domain.game.Participant;

public class Score {
    private static final int BUSTED_STANDARD_SCORE_VALUE = 21;
    private static final int BLACKJACK_STANDARD_CARD_COUNT = 2;
    private static final int BLACKJACK_STANDARD_SCORE_VALUE = 21;

    private final int scoreValue;
    private final int cardCount;

    public Score(Participant participant) {
        this.scoreValue = participant.getOptimisticValue();
        this.cardCount = participant.getCards().size();
    }

    public boolean isBusted() {
        return scoreValue > BUSTED_STANDARD_SCORE_VALUE;
    }

    public boolean isBlackJack() {
        return cardCount == BLACKJACK_STANDARD_CARD_COUNT & scoreValue == BLACKJACK_STANDARD_SCORE_VALUE;
    }

    public GameResult calculateGameResult(Score comparedScore) {
        if (isBlackJack() || comparedScore.isBlackJack()) {
            return calculateResultOfBlackjack(comparedScore);
        }

        if (isBusted() || comparedScore.isBusted()) {
            return calculateResultOfBusted(comparedScore);
        }

        GameResultType gameResultType = GameResultType.find(this.scoreValue, comparedScore.getScoreValue());
        return new GameResult(gameResultType, false);
    }

    private GameResult calculateResultOfBlackjack(Score comparedScore) {
        if (isBlackJack() && comparedScore.isBlackJack()) {
            return new GameResult(GameResultType.TIE, true);
        }

        if (isBlackJack() && !comparedScore.isBlackJack()) {
            return new GameResult(GameResultType.WIN, true);
        }

        if (!isBlackJack() && comparedScore.isBlackJack()) {
            return new GameResult(GameResultType.LOSE, false);
        }

        throw new IllegalArgumentException("정의되지 않은 결과입니다.");
    }

    private GameResult calculateResultOfBusted(Score comparedScore) {
        if (isBusted() && comparedScore.isBusted()) {
            return new GameResult(GameResultType.TIE, false);
        }

        if (isBusted() && !comparedScore.isBusted()) {
            return new GameResult(GameResultType.LOSE, false);
        }

        if (!isBusted() && comparedScore.isBusted()) {
            return new GameResult(GameResultType.WIN, false);
        }

        throw new IllegalArgumentException("정의되지 않은 결과입니다.");
    }

    public int getScoreValue() {
        return scoreValue;
    }
}
