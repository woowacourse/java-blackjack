package model.paticipant;

import static model.judgement.Profit.ZERO;

import java.util.List;
import model.card.Card;
import model.judgement.Profit;
import model.judgement.ResultStatus;

public interface Player {

    String getName();

    int calculateTotalScore();

    boolean canHit();

    void addCard(model.card.Card card);

    boolean isBust();

    boolean isBlackjack();

    List<Card> getCards();

    default Profit calculateProfit(ResultStatus resultStatus) {
        return ZERO;
    }
}
