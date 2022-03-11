package domain.util;

import domain.card.PlayingCard;
import java.util.List;
import java.util.stream.IntStream;

public class ScoreUtil {
    private static final int MAXIMUM_FOR_BLACKJACK = 21;
    private static final int MULTIPLIER_FOR_MAX_CASE = 11;
    private static final int INTERVAL_FOR_SUM_CASES = 10;
    private static final int MINIMUM_SUM = 0;

    private ScoreUtil() {
    }

    public static int getScore(List<PlayingCard> playingCards) {
        if (containsAce(playingCards)) {
            return getOptimizedScore(playingCards);
        }

        return playingCards.stream()
                .mapToInt(ScoreUtil::getScoreByPlayingCard)
                .sum();
    }

    private static boolean containsAce(List<PlayingCard> playingCards) {
        return playingCards.stream()
                .anyMatch(PlayingCard::isAce);
    }

    private static int getOptimizedScore(List<PlayingCard> playingCards) {
        int preSum = playingCards.stream()
                .filter(playingCard -> !playingCard.isAce())
                .mapToInt(ScoreUtil::getScoreByPlayingCard)
                .sum();

        int aceCount = (int) playingCards.stream()
                .filter(PlayingCard::isAce)
                .count();

        return calculateOptimizeScore(preSum, aceCount);
    }

    private static int calculateOptimizeScore(int preSum, int aceCount) {
        int targetScore = MAXIMUM_FOR_BLACKJACK - preSum;

        return IntStream.iterate(
                        aceCount * MULTIPLIER_FOR_MAX_CASE,
                        i -> i > MINIMUM_SUM,
                        i -> i - INTERVAL_FOR_SUM_CASES
                )
                .filter(i -> i <= targetScore)
                .max()
                .orElse(aceCount) + preSum;
    }

    private static int getScoreByPlayingCard(PlayingCard playingCard) {
        return playingCard.getScore();
    }
}
