package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hands {

    private static final int WINNING_BASELINE = 21;

    private final List<Card> cards;

    public Hands(final List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculate() {
        int sum = sumWithoutAce();
        for (int i = 0; i < countAce(); ++i) {
            sum += properAce(sum);
        }
        return sum;
    }

    private int properAce(int sum) {
        if (sum + CardValue.ACE.getValue() > WINNING_BASELINE) {
            return 1;
        }
        return CardValue.ACE.getValue();
    }

    public boolean containsAce() {
        return cards.stream()
                .map(Card::getCardValue)
                .anyMatch(CardValue::isAce);
    }

    private int sumWithoutAce() {
        return cards.stream()
                .filter(card -> !CardValue.isAce(card.getCardValue()))
                .map(Card::getValue)
                .reduce(Integer::sum)
                .get();
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(card -> CardValue.isAce(card.getCardValue()))
                .count();
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
