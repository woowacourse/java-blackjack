package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cards {

    public static final int BLACKJACK = 21;
    private static final int ACE_CONVERSION = 10;
    public static final int INITIAL_CARDS_SIZE = 2;
    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public void of(List<Card> cards) {
        if (cards.size() != INITIAL_CARDS_SIZE) {
            throw new IllegalArgumentException("[ERROR] 초기 카드는 2장입니다.");
        }
        this.cards.addAll(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public boolean containsAce() {
        return cards.stream()
                .map(Card::getCardValue)
                .anyMatch(Denomination::isAce);
    }

    public int sumWithoutAce() {
        return cards.stream()
                .filter(card -> !Denomination.isAce(card.getCardValue()))
                .map(Card::getPoint)
                .reduce(0, Integer::sum);
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(card -> Denomination.isAce(card.getCardValue()))
                .count();
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
        return checkSumOfFirstTwoCards();
    }

    private boolean checkSumOfFirstTwoCards() {
        int sum = properSum(
                IntStream.range(0, INITIAL_CARDS_SIZE)
                        .map(i -> getPoint(cards.get(i)))
                        .sum()
        );
        return BLACKJACK == sum;
    }

    public int calculate() {
        int sum = sumWithoutAce() + (Denomination.ACE.getPoint() * countAce());
        if (containsAce()) {
            sum = properSum(sum);
        }
        return sum;
    }

    private int properSum(int sum) {
        if (sum + ACE_CONVERSION > BLACKJACK) {
            return sum;
        }
        return sum + ACE_CONVERSION;
    }

    private int getPoint(Card card) {
        return card.getPoint();
    }

    public boolean isBust() {
        return calculate() > BLACKJACK;
    }
}
