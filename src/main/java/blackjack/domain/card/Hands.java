package blackjack.domain.card;

import blackjack.domain.Score;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hands {

    private static final int INITIAL_SIZE = 2;
    private static final int TEN = 10;
    private static final int WINNING_BASELINE = 21;

    private final List<Card> cards;
    private final boolean isBlackjack;

    public Hands(final List<Card> cards) {
        if (cards.size() != INITIAL_SIZE) {
            throw new IllegalArgumentException("[ERROR] 초기 카드는 2장입니다.");
        }
        this.cards = cards;
        isBlackjack = validateBlackjack();
    }

    private boolean validateBlackjack() {
        return calculate() == WINNING_BASELINE;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculate() {
        int sum = sumWithoutAce() + Denomination.ACE.getValue() * countAce();
        if (containsAce()) {
            sum = properSum(sum);
        }
        return sum;
    }

    private int properSum(int sum) {
        if (sum + 10 > WINNING_BASELINE) {
            return sum;
        }
        return sum + 10;
    }

    public boolean containsAce() {
        return cards.stream()
                .map(Card::getCardValue)
                .anyMatch(Denomination::isAce);
    }

    private int sumWithoutAce() {
        return cards.stream()
                .filter(card -> !Denomination.isAce(card.getCardValue()))
                .map(Card::getValue)
                .reduce(0, Integer::sum);
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(card -> Denomination.isAce(card.getCardValue()))
                .count();
    }

    public List<Card> toList() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> cardsOf(int number) {
        return IntStream.range(0, number)
                .mapToObj(cards::get)
                .collect(Collectors.toList());
    }

    public boolean isBlackjack() {
        return isBlackjack;
    }
}
