package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        this(new ArrayList<>());
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int getTotalNumber() {
        return CardNumber.getOptimizeTotalNumber(
            cards.stream()
            .map(Card::getCardNumber)
            .collect(Collectors.toList())
        );
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
