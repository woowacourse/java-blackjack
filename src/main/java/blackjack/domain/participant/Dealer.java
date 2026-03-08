package blackjack.domain.participant;

public class Dealer extends Participant {
    
    private static final String DEALER_NICKNAME = "딜러";
    
    public Dealer() {
        super(DEALER_NICKNAME);
    }
    
    public String getFirstCard() {
        return hand.getFirstCard().getDisplayName();
    }
    
    public boolean isDealerDraw() {
        return hand.isDealerDraw();
    }
    
    @Override
    public boolean isDealer() {
        return true;
    }
}
