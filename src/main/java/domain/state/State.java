package domain.state;

import domain.card.vo.Card;
import domain.score.Score;
import java.util.List;

public interface State {
    boolean isFinished();

    State drawCard(Card card);

    Score getScore();

    List<Card> getCards();

    State stay();

    Integer getProfit(State dealerState, Integer betCost);
}
