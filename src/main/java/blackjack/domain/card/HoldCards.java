package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HoldCards {
    private final List<Card> cards;

    public HoldCards(Card card1, Card card2) {
        this.cards = new ArrayList<>();
        this.cards.add(card1);
        this.cards.add(card2);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int countBestNumber() {
        return Number.sum(cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.toList()));
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public List<Card> getCards() {
        return cards;
    }
}
