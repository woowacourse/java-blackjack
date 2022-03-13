package domain.participant;

import dto.ResultDto;

import java.util.List;

public class Dealer extends Participant {

    private static final int MAX_CARD_SUM = 16;
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public ResultDto checkResult(List<Result> playersResult) {
        int winCount = countTargetResult(playersResult, Result.LOSE);
        int loseCount = countTargetResult(playersResult, Result.WIN);
        int drawCount = playersResult.size() - winCount - loseCount;

        return ResultDto.of(NAME, winCount, drawCount, loseCount);
    }

    private int countTargetResult(List<Result> playersResult, Result targetResult) {
        return (int) playersResult.stream()
            .filter(result -> result == targetResult)
            .count();
    }

    @Override
    public boolean canDrawCard() {
        return cards.calculateSum() <= MAX_CARD_SUM;
    }
}
