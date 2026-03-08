package blackjack.domain.participant;

import blackjack.domain.PlayingCards;

public class Dealer extends Participant {
    
    private static final String DEALER_NICKNAME = "딜러";

    private Dealer(String nickname, Role role) {
        super(nickname, PlayingCards.createEmptyHands(), role);
    }

    public static Dealer from() {
        return new Dealer(DEALER_NICKNAME, Role.DEALER);
    }
    
    public String getFirstCard() {
        return hand.getFirstCard().getDisplayName();
    }
    
    public boolean isDealerDraw() {
        return hand.isDealerDraw();
    }
}
