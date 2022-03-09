package domain;

import java.util.List;

public class Dealer extends Human {

    Cards cards = new Cards();

    public Dealer(List<Card> initCards) {
        cards.add(initCards);
    }

    public boolean isReceived() {
        int total = cards.calculateTotal();

        return total <= 16;
    }

    @Override
    public void receiveCard(final Card card) {
        cards.add(card);
    }
}
