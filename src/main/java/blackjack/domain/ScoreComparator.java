package blackjack.domain;

public interface ScoreComparator {

    boolean compare(int userScore, int dealerScore);
}
