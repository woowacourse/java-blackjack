package domain.rule;

import domain.GameResult;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.Set;

public class BlackjackRule {
    private static final int SUM_LIMIT = 21;

    public boolean isBlackjack(Cards cards) {
        return false;
    }

    public boolean isBust(Cards cards) {
        Set<Integer> coordinates = cards.getCoordinateSums();
        return coordinates.stream().noneMatch(coordinate -> coordinate <= SUM_LIMIT);
    }

    public boolean isWin(Cards cards) {
        return getScore(cards) == SUM_LIMIT;
    }

    public int getScore(Cards cards) {
        Set<Integer> coordinates = cards.getCoordinateSums();
        if (isBust(cards)) {
            return getMinSum(coordinates);
        }
        return getMaxSum(coordinates);
    }

    public GameResult getResult(Player player, Dealer dealer) {
        if (isBust(player.getCards())) {
            return GameResult.LOSE;
        }
        if (isBust(dealer.getCards()) || getScore(player.getCards()) > getScore(dealer.getCards())) {
            return GameResult.WIN;
        }
        if (getScore(player.getCards()) < getScore(dealer.getCards())) {
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
