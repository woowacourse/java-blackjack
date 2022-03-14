package blackjack.domain;

import java.util.List;

public class Dealer extends Human {

    public static final int RECEIVED_MAXIMUM = 16;
    private static final String name = "딜러";

    private final Cards cards = new Cards();

    public boolean compare(final Player player) {
        return getTotal() >= player.getTotal();
    }

    public static String getName() {
        return name;
    }

    public List<String> getInitCard() {
        return cards.getDealerInitCard();
    }

    @Override
    public List<String> getCards() {
        return cards.getAllCards();
    }

    @Override
    public int getTotal() {
        return cards.calculateTotal();
    }

    @Override
    public void dealInit(final List<Card> initCards) {
        cards.add(initCards);
    }

    @Override
    public void hit(final Card card) {
        cards.add(card);
    }

    @Override
    public boolean isBust() {
        return cards.isOverBlackjack();
    }

    @Override
    public boolean canDraw() {
        return cards.calculateTotal() <= RECEIVED_MAXIMUM;
    }
}
