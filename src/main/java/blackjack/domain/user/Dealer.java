package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends User {

    private static final String DEALER = "딜러";
    public static final int THRESHOLD = 16;
    private static final int CARD_SHOW_LIMIT = 1;

    public Dealer() {
        super(DEALER);
    }

    @Override
    public List<Card> getInitialCards() {
        return this.cards.subList(0, CARD_SHOW_LIMIT);
    }

    public boolean isUnderThreshold() {
        return (this.getTotalScore() <= THRESHOLD && !this.isBusted());
    }
}
