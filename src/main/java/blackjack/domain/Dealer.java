package blackjack.domain;

import java.util.List;

public class Dealer extends Human {

    public static final int RECEIVED_MAXIMUM = 16;
    private static final String name = "딜러";

    public static String getName() {
        return name;
    }

    public boolean isLowerScore(final Player player) {
        return showSumOfCards() < player.showSumOfCards();
    }

    public List<String> showPartOfCards() {
        return cards.getPartOfDealerCard();
    }

    @Override
    public boolean isDrawable() {
        return cards.calculateTotal() <= RECEIVED_MAXIMUM;
    }
}
