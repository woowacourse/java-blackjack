package blackjack.domain;

import java.util.List;

public class Dealer extends Human {

    public static final int RECEIVED_MAXIMUM = 16;
    private static final String name = "딜러";

    private final Cards cards = new Cards();

    public static String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards.getAllCards();
    }

    public void receiveInitCard(final List<Card> initCards) {
        cards.add(initCards);
    }

    public boolean isReceived() {
        int total = cards.calculateTotal();

        return total <= RECEIVED_MAXIMUM;
    }

    @Override
    public void receiveCard(final Card card) {
        cards.add(card);
    }

    public boolean compare(Player player) {
        return getTotal() >= player.getTotal();
    }

    public int getTotal() {
        return cards.calculateTotal();
    }

    public List<String> getInitCard() {
        return cards.getDealerInitCard();
    }
}
