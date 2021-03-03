package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hands {

    private final List<Card> cards;

    public Hands(final List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculate() {
        int point = sum();
        if (containsAce() && point > 21) {
            point -= 10;
        }
        return point;
    }

    public int sum() {
        return cards.stream()
                .map(Card::getValue)
                .reduce(Integer::sum)
                .get();
    }

    public boolean containsAce() {
        return cards.stream()
                .map(Card::getCardValue)
                .anyMatch(CardValue::isAce);
    }

    public List<Card> toList() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> getCardOf(int number) {
        return IntStream.range(0, number)
                .mapToObj(cards::get)
                .collect(Collectors.toList());
    }
}
