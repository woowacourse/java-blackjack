package domain.rule;

import domain.card.Cards;
import java.util.Set;

public class BlackjackRule {
    private static final int SUM_LIMIT = 21;
    private static final int BLACKJACK_COUNT = 2;

    public boolean isBlackjack(Cards cards) {
        return cards.getCount() == BLACKJACK_COUNT && isHit(cards);
    }

    public boolean isBust(Cards cards) {
        Set<Integer> coordinates = cards.getCoordinateSums();
        return coordinates.stream().noneMatch(coordinate -> coordinate <= SUM_LIMIT);
    }

    public boolean isHit(Cards cards) {
        return getScore(cards) == SUM_LIMIT;
    }

    public int getScore(Cards cards) {
        Set<Integer> coordinates = cards.getCoordinateSums();
        if (isBust(cards)) {
            return getMinSum(coordinates);
        }
        return getMaxSum(coordinates);
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
