package domain.rule;

import domain.GameResult;
import domain.card.Hand;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.Set;

public class BlackjackRule implements GameRule {
    private static final int SUM_LIMIT = 21;
    private static final int ACE_BONUS = 10;
    private static final BlackjackRule INSTANCE = new BlackjackRule();  // 캐싱된 인스턴스

    public static BlackjackRule getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isBurst(Hand hand) {
        return hand.getCoordinateSums() > SUM_LIMIT;
    }

    public boolean isBurst2(int totalScore) {
        return totalScore > SUM_LIMIT;
    }

    @Override
    public boolean isBlackJack(Hand hand) {
        int coordinates = hand.getCoordinateSums();
        return coordinates == SUM_LIMIT;
    }

    @Override
    public int getScore(Hand hand) {
        int totalScore = hand.getCoordinateSums();
        if (isBurst2(totalScore + ACE_BONUS)) {
            return totalScore;
        }
        return totalScore + ACE_BONUS;
    }

    // TODO: 파라미터로 누구의 카드인지 구별해야 하는가? (self는 Player, other는 Dealer여야 함)
    @Override
    public GameResult getResult(Player player, Dealer dealer) {
        if (isBurst(player.getCards())) {
            return GameResult.LOSE;
        }
        if (isBurst(dealer.getCards()) || getScore(player.getCards()) > getScore(dealer.getCards())) {
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
