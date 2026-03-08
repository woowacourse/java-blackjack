package blackjack.domain.participant;

public class Dealer extends Participant {
    
    public Dealer() {
        super(Role.DEALER);
    }
    
    public String getFirstCard() {
        return hand.getFirstCard().getDisplayName();
    }
    
    public boolean isDealerDraw() {
        return hand.isDealerDraw();
    }
}
