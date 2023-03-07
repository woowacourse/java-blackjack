package domain.user;

import domain.Card.Card;
import domain.Card.CardCollection;

public interface Participant2 {
    
    void addCard(Card card);
    
    CardCollection getReadyCards();
    
    CardCollection getCards();
    
    boolean isAbleToDraw();
    
}
