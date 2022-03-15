package blackjack.domain;

import java.util.List;
import java.util.jar.Attributes;

public class Dealer extends Human {

    public static final int RECEIVED_MAXIMUM = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    private final Cards cards = new Cards();

    public boolean compare(final Player player) {
        return getTotal() >= player.getTotal();
    }

    public String getName() {
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
