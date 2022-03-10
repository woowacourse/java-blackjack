package domain.participant;

import java.util.Arrays;
import java.util.List;

public class Dealer extends Participant {

    private static final int MAX_CARD_SUM = 16;
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public List<Integer> checkWinCount(Players players) {
        List<Result> playersResult = players.checkPlayersResult(this);

        int winCount = (int) playersResult.stream()
            .filter(result -> result == Result.LOSE)
            .count();

        int loseCount = (int) playersResult.stream()
            .filter(result -> result == Result.WIN)
            .count();

        int drawCount = players.getSize() - winCount - loseCount;
        return Arrays.asList(winCount, drawCount, loseCount);
    }

    @Override
    public boolean canDrawCard() {
        return cards.calculateSum() <= MAX_CARD_SUM;
    }
}
