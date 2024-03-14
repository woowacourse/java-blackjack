package blackjack.domain;

public interface State {
    State draw(Deck deck);

    State stand();

    Score calculateHand();

    boolean isFinished();

    Hand getHand();
}
