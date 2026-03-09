package blackjack.domain.participant;

import blackjack.domain.PlayingCards;

public class Dealer extends Participant {

    private final int DEALER_SCORE = 16;

    private final static String DEALER_NICKNAME = "딜러";

    private Dealer(String nickname, Role role) {
        super(nickname, PlayingCards.createEmptyHands(), role);
    }

    public static Dealer from() {
        return new Dealer(DEALER_NICKNAME, Role.DEALER);
    }

    public String getDealerNickname() {
        return DEALER_NICKNAME;
    }

    public String getFirstCard() {
        return hand.getFirstCard().getDisplayName();
    }

    public boolean isDealerDraw() {
        return hand.calculateTotalScore() <= DEALER_SCORE;
    }
}
