package blackjack.domain;

import java.util.List;

public class Dealer extends Human {

    public static final int RECEIVED_MAXIMUM = 16;
    public static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public boolean isLowerScore(final Player player) {
        return showSumOfCards() < player.showSumOfCards();
    }

    public List<Card> showPartOfCards() {
        return playingCards.getPartOfDealerCard();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isDrawable() {
        return playingCards.calculateTotal() <= RECEIVED_MAXIMUM;
    }
}
