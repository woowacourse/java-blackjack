package domain;

import java.util.List;

public class Dealer extends Human {

    public static final int RECEIVED_MAXIMUM = 16;
    Cards cards = new Cards();

    public Dealer(List<Card> initCards) {
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
}
