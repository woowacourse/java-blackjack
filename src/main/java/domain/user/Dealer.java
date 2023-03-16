package domain.user;

import domain.card.Card;
import domain.card.Hand;

public class Dealer implements Playable {
    
    public static final int DEALER_DRAWABLE_BOUNDARY = 17;
    private final String name = DEALER_NAME;
    private Hand hand = new Hand();
    
    @Override
    public void addCard(final Card card) {
        this.hand = this.hand.add(card);
    }
    
    @Override
    public Hand getReadyCards() {
        if (this.hand.size() != INITIAL_HAND_SIZE) {
            throw new IllegalStateException(FIRST_HAND_STATUS_ERROR_MESSAGE);
        }
        return new Hand().add(this.hand.get(0));
    }
    
    @Override
    public Hand getCards() {
        return this.hand;
    }
    
    @Override
    public boolean isAbleToDraw() {
        return this.hand.calculateScore() < DEALER_DRAWABLE_BOUNDARY;
    }
    
    @Override
    public MemberStatus getStatus() {
        return MemberStatus.of(this.hand.calculateScore(), this.hand.size());
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
    
    
    public boolean isBlackJack() {
        return this.getStatus().isBlackJack();
    }
}
