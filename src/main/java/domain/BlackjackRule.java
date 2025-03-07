package domain;

import java.util.Set;

public class BlackjackRule implements GameRule {
    private static final int SUM_LIMIT = 21;

    @Override
    public boolean isBurst(Cards cards) {
        Set<Integer> coordinates = cards.getCoordinateSums();
        return coordinates.stream().noneMatch(coordinate -> coordinate <= SUM_LIMIT);
    }

    @Override
    public int getScore(Cards cards) {
        Set<Integer> coordinates = cards.getCoordinateSums();
        if (isBurst(cards)) {
            return getMinSum(coordinates);
        }
        return getMaxSum(coordinates);
    }

    // TODO: 파라미터로 누구의 카드인지 구별해야 하는가? (self는 Player, other는 Dealer여야 함)
    @Override
    public GameResult getResult(Cards self, Cards other) {
        if (isBurst(self)) {
            return GameResult.LOSE;
        }
        if (isBurst(other) || getScore(self) > getScore(other)) {
            return GameResult.WIN;
        }
        if (getScore(self) < getScore(other)) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    private int getMinSum(Set<Integer> coordinates) {
        return coordinates.stream()
                .mapToInt(i -> i)
                .min()
                .orElseThrow(() -> new IllegalStateException("카드가 존재하지 않는 플레이어입니다."));
    }

    private int getMaxSum(Set<Integer> coordinates) {
        return coordinates.stream()
                .filter(coordinate -> coordinate <= SUM_LIMIT)
                .mapToInt(i -> i)
                .max()
                .orElseThrow(() -> new IllegalStateException("카드가 존재하지 않는 플레이어입니다."));
    }
}
