package blackjack.domain.result;

public interface ScoreComparator {

    boolean compare(int userScore, int dealerScore);
}
