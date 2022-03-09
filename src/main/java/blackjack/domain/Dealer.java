package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private final List<Card> cards = new ArrayList<>();

    private Dealer() {
    }

    public static Dealer startWithTwoCards(final Deck deck) {
        final Dealer dealer = new Dealer();
        dealer.drawCard(deck);
        dealer.drawCard(deck);
        return dealer;
    }

    public void drawCard(final Deck deck) {
        cards.add(deck.drawCard());
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
