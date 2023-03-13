package blackjack.domain.participants;

import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;

import java.util.Map;
import java.util.stream.Collectors;

public class Dealer extends Participant {

    private static final int DEALER_DRAW_POINT = 16;

    public static Map<ResultType, Integer> calculateResult(final Map<String, ResultType> resultOfPlayers) {
        return resultOfPlayers.entrySet()
                .stream()
                .collect(Collectors.groupingBy(entry -> entry.getValue()
                        .getOpposite(), Collectors.summingInt((ignored) -> 1)));

    }

    @Override
    public boolean isDrawable() {
        final Score currentScore = currentScore();
        return currentScore.getValue() <= DEALER_DRAW_POINT;
    }

    public int getDrawPoint() {
        return DEALER_DRAW_POINT;
    }

}
