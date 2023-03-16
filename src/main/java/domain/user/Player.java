package domain.user;

import domain.card.Card;
import domain.card.Hand;
import domain.money.Bet;

public class Player implements Playable {
    
    private final PlayerName name;
    
    private Bet bet;
    
    private Hand hand = new Hand();
    
    public Player(final String name) {
        this.name = new PlayerName(name);
        this.bet = new Bet(0);
    }
    
    public void addBet(Bet bet) {
        this.bet = this.bet.add(bet);
    }
    
    @Override
    public void addCard(final Card card) {
        this.hand = this.hand.add(card);
    }
    
    @Override
    public Hand getReadyCards() {
        if (this.hand.size() != 2) {
            throw new IllegalStateException(FIRST_HAND_STATUS_ERROR_MESSAGE);
        }
        return this.hand;
    }
    
    @Override
    public Hand getCards() {
        return this.hand;
    }
    
    @Override
    public boolean isAbleToDraw() {
        return this.hand.calculateScore() < DRAWABLE_BOUNDARY;
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
    
    public String getName() {
        return this.name.getValue();
    }
    
    public boolean isBlackJack() {
        return this.getStatus().isBlackJack();
    }
    
    public Bet getBet() {
        return this.bet;
    }
}
