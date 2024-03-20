package domain.participant.state;

import domain.card.Card;

import java.util.List;

public interface State {
    State draw(Card card);

    State stand();

    int score();

    double profitRate(State state);

    boolean isBust();

    boolean isBlackjack();

    boolean isHit();

    List<Card> hand();
}
