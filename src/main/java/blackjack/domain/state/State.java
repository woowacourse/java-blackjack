package blackjack.domain.state;

public interface State {
    boolean isEndState();
    double profit();
    State changeState();
}