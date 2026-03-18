package domain.state;

import domain.WinningStatus;
import domain.card.Card;

import java.util.List;

public interface ParticipantState {
    ParticipantState draw(Card card);
    ParticipantState onStay();

    WinningStatus calculateWinningStatus(ParticipantState dealerState);

    boolean isFinished();
    boolean isBust();
    boolean isBlackJack();

    int getScore();
    List<Card> getCards();
    Card getFirstCard();
    int getHandSize();
}
