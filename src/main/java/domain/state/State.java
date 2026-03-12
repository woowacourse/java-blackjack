package domain.state;

import domain.card.vo.Card;
import java.util.List;

public interface State {
    boolean isFinished();

    State drawCard(Card card);

    Integer getScore();

    List<Card> getCards();

    State stay();

    Integer getProfit(Integer betCost);
}
