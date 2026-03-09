package domain;

import domain.card.Card;
import domain.player.HandCards;
import domain.vo.Name;

public class Participant {
    protected Name name;
    protected HandCards handCards;
    private static final int BUST_CONDITION = 21;

    public void drawCard(Card card){
        handCards.addCard(card);
    }

    public boolean isBust() {
        return handCards.calculateCards() > BUST_CONDITION;
    }

    public int getMyScore(){
        return handCards.calculateCards();
    }
}
