package domain.user;

import domain.Card.Card;
import domain.Card.CardCollection;

public class Dealer implements Playable {
    
    private final String name = "딜러";
    private CardCollection hand = new CardCollection();
    
    @Override
    public void addCard(final Card card) {
        this.hand = this.hand.add(card);
    }
    
    @Override
    public CardCollection getReadyCards() {
        if (this.hand.size() != 2) {
            throw new IllegalStateException("처음에는 2장의 카드만 가질 수 있습니다.");
        }
        return new CardCollection().add(this.hand.get(0));
    }
    
    @Override
    public CardCollection getCards() {
        return this.hand;
    }
    
    @Override
    public boolean isAbleToDraw() {
        return this.hand.calculateScore() < 17;
    }
    
    @Override
    public ParticipantStatus getStatus() {
        return ParticipantStatus.of(this.hand.calculateScore());
    }
    
    @Override
    public int getScore() {
        return this.hand.calculateScore();
    }
    
    @Override
    public boolean isBust() {
        return this.getStatus().isBust();
    }
    
    @Override
    public String getName() {
        return this.name;
    }
}
