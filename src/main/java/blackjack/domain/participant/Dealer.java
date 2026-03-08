package blackjack.domain.participant;

public class Dealer extends Participant {
    
    private static final String DEALER_NICKNAME = "딜러";
    private static final int DEALER_SCORE = 16;
    
    public Dealer() {
        super(DEALER_NICKNAME);
    }
    
    public String getFirstCard() {
        return hand.getFirstCardDisplayName();
    }
    
    public boolean isDealerDraw() {
        return hand.calculateTotalScoreForResult(BUSTED_SCORE) <= DEALER_SCORE;
    }
    
    @Override
    public boolean isDealer() {
        return true;
    }
}
