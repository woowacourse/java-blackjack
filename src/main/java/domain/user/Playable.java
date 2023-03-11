package domain.user;

import domain.card.Card;
import domain.card.Hand;

public interface Playable {
    
    void addCard(Card card);
    
    Hand getReadyCards();
    
    Hand getCards();
    
    boolean isAbleToDraw();
    
    MemberStatus getStatus();
    
    int getScore();
    
    boolean isBust();
    
    String getName();
}
