package model.paticipant;

import model.judgement.Profit;
import model.judgement.ResultStatus;

public interface Player {

    String getName();

    int calculateTotalScore();

    boolean canHit();

    void addCard(model.card.Card card);

    boolean isBust();

    java.util.List<model.card.Card> getCards();

    default Profit calculateProfit(ResultStatus resultStatus) {
        return model.judgement.Profit.ZERO;
    }
}
