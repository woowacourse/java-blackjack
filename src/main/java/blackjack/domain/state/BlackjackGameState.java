package blackjack.domain.state;

public interface BlackjackGameState {

    BlackjackGameState hit();

    BlackjackGameState stay();

    boolean isFinished();

    double earningRate(BlackjackGameState blackjackGameState);
}
