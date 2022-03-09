package blackJack.participant;

import java.util.ArrayList;
import java.util.List;

import blackJack.domain.Card;

public class Dealer {

    private final String name = "딜러";
    private final List<Card> cards;

    public Dealer() {
        this.cards = new ArrayList<>();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        return cards.stream()
            .mapToInt(Card::getScore)
            .sum();
    }
}
