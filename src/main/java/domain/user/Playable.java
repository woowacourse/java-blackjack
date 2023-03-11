package domain.user;

import domain.card.Card;
import domain.card.Hand;

public interface Playable {
    
    String DEALER_NAME = "딜러";
    int INITIAL_HAND_SIZE = 2;
    String FIRST_HAND_STATUS_ERROR_MESSAGE = "처음에는 2장의 카드만 가질 수 있습니다.";
    
    void addCard(Card card);
    
    Hand getReadyCards();
    
    Hand getCards();
    
    boolean isAbleToDraw();
    
    MemberStatus getStatus();
    
    int getScore();
    
    boolean isBust();
    
    String getName();
}
