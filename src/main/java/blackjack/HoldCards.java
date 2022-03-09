package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HoldCards {
    private final List<Card> deck;

    public HoldCards() {
        this.deck = new ArrayList<>();
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public int countBestNumber() {
        return Number.sum(deck.stream()
                .map(Card::getNumber)
                .collect(Collectors.toList()));
    }
}
