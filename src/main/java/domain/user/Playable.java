package domain.user;

import domain.Card.Card;
import domain.Card.CardCollection;

public interface Playable {
    
    void addCard(Card card);
    
    CardCollection getReadyCards();
    
    CardCollection getCards();
    
    boolean isAbleToDraw();
    
    ParticipantStatus getStatus();
    
    int getScore();
    
    boolean isBust();
    
    String getName();
}
