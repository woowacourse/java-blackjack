package blackjack.domain;

import java.util.List;

public class Dealer extends Human {

    public static final int RECEIVED_MAXIMUM = 16;
    private static final String name = "딜러";

    private final Cards cards = new Cards();

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
    public List<String> showCards() {
        return cards.getAllCards();
    }

    @Override
    public int showSumOfCards() {
        return cards.calculateTotal();
    }

    @Override
    public void receiveInitCards(final List<Card> initCards) {
        cards.add(initCards);
    }

    @Override
    public void receiveCard(final Card card) {
        cards.add(card);
    }

    @Override
    public boolean isBust() {
        return cards.isOverBlackjack();
    }

    @Override
    public boolean isReceived() {
        return cards.calculateTotal() <= RECEIVED_MAXIMUM;
    }
}
