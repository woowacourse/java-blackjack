package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends User {

    public static final String DEALER = "딜러";
    public static final int THRESHOLD = 16;
    public static final int INITIAL_SHOW_CARD_AMOUNT = 1;

    public Dealer() {
        super(DEALER);
    }

    public boolean isUnderThreshold() {
        return cards.calculateTotalScore() <= THRESHOLD;
    }

    public int compareScoreWith(Player player) {
        return this.getTotalScore() - player.getTotalScore();
    }

    @Override
    public List<Card> getInitialCards() {
        return this.cards.getCards(INITIAL_SHOW_CARD_AMOUNT);
    }
}
