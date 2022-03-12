package blackjack.domain.result;

public interface ResultDeterminer {

    boolean compare(int userScore, int dealerScore);
}
