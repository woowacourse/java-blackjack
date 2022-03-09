package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    protected List<Card> cards = new ArrayList<>();

    public void drawCard(final Deck deck) {
        cards.add(deck.drawCard());
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public boolean isNotBurst() {
        int totalSum = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();

        return totalSum > 21;
    }
}
