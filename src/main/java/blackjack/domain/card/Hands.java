package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hands {

    private static final int WINNING_BASELINE = 21;
    private static final int ACE_CONVERSION = 10;

    private final List<Card> cards = new ArrayList<>();
    private boolean isBlackjack;

    public Hands() {

    }

    public void makeWith(List<Card> initialCards) {
        if (initialCards.size() != 2) {
            throw new IllegalArgumentException("[ERROR] 초기 카드는 2장입니다.");
        }
        cards.addAll(initialCards);
        isBlackjack = validateBlackjack();
    }

    private boolean validateBlackjack() {
        return calculate() == WINNING_BASELINE;
    }

    public void addCard(Card card) {
        cards.add(card);
        if (isBlackjack) { isBlackjack = false;}
    }

    public int calculate() {
        int sum = sumWithoutAce() + Denomination.ACE.getPoint() * countAce();
        if (containsAce()) {
            sum = properSum(sum);
        }
        return sum;
    }

    private int properSum(int sum) {
        if (sum + ACE_CONVERSION > WINNING_BASELINE) {
            return sum;
        }
        return sum + ACE_CONVERSION;
    }

    public boolean containsAce() {
        return cards.stream()
                .map(Card::getCardValue)
                .anyMatch(Denomination::isAce);
    }

    private int sumWithoutAce() {
        return cards.stream()
                .filter(card -> !Denomination.isAce(card.getCardValue()))
                .map(Card::getPoint)
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

    public List<Card> getCardOf(int number) {
        return IntStream.range(0, number)
                .mapToObj(cards::get)
                .collect(Collectors.toList());
    }

    public boolean isBlackjack() {
        return isBlackjack;
    }
}
