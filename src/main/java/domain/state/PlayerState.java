package domain.state;

import domain.card.Card;

import java.util.List;

public interface PlayerState {
    PlayerState draw(Card card);
    PlayerState onStay();

    boolean isFinished();
    boolean isBust();
    boolean isBlackJack();

    int getScore();
    List<Card> getCards();
    Card getFirstCard();
    int getHandSize();
}
