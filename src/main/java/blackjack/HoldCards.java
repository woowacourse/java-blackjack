package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HoldCards {
    private final List<Card> cards;

    public HoldCards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int countBestNumber() {
        return Number.sum(cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.toList()));
    }
}
