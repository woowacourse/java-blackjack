package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HoldingCards {

    private final List<Card> cards = new ArrayList<>();

    public void initialCard(Card card1, Card card2) {
        cards.addAll(List.of(card1, card2));
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int sum() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }
}
