package blackjack.domain.result;

@FunctionalInterface
public interface ScoreComparator {

    boolean compare(int userScore, int dealerScore);
}
