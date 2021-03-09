package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cards {

    public static final int BLACKJACK = 21;
    private static final int ACE_CONVERSION = 10;

    private final List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards of(List<Card> cards) {
        return new Cards(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public boolean containsAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCardsWithSize(int number) {
        return IntStream.range(0, number)
                .mapToObj(cards::get)
                .collect(Collectors.toList());
    }

    public List<Card> toList() {
        return Collections.unmodifiableList(cards);
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && calculate() == BLACKJACK;
    }

    public int calculate() {
        int sum = sumWithoutAce() + (Denomination.ACE.getScore() * countAce());
        if (containsAce()) {
            sum = properSum(sum);
        }
        return sum;
    }

    public int sumWithoutAce() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .map(Card::getScore)
                .reduce(0, Integer::sum);
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int properSum(int sum) {
        if (sum + ACE_CONVERSION > BLACKJACK) {
            return sum;
        }
        return sum + ACE_CONVERSION;
    }

    public boolean isBust() {
        return calculate() > BLACKJACK;
    }
}
