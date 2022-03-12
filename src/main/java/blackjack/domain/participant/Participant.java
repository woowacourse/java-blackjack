package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public abstract class Participant {

    private static final int INITIAL_CARDS_SIZE = 2;
    private static final int BLACKJACK_COUNT = 2;
    private static final int BLACKJACK_SCORE = 21;

    private final Name name;
    private final List<Card> cards;

    protected Participant(Name name, List<Card> cards) {
        cards = new ArrayList<>(cards);
        validateCards(cards);
        validateName(name);

        this.name = name;
        this.cards = cards;
    }

    private void validateName(Name name) {
        Objects.requireNonNull(name, "[ERROR] 이름은 null일 수 없습니다.");
    }

    private void validateCards(List<Card> cards) {
        Objects.requireNonNull(cards, "[ERROR] 카드는 null일 수 없습니다.");
        validateSize(cards);
        validateDistinct(cards);
    }

    private void validateSize(List<Card> cards) {
        if (cards.size() != INITIAL_CARDS_SIZE) {
            throw new IllegalArgumentException("[ERROR] 카드를 두 장 받고 시작해야 합니다.");
        }
    }

    private void validateDistinct(List<Card> cards) {
        if (cards.stream().distinct().count() != cards.size()) {
            throw new IllegalArgumentException("[ERROR] 카드는 중복될 수 없습니다.");
        }
    }

    public void hit(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int aceCount = calculateAceCount();
        int sum = calculateMinimumSum();
        return calculateSum(aceCount, sum);
    }

    private int calculateSum(int aceCount, int sum) {
        return IntStream.rangeClosed(0, aceCount)
            .map(i -> sum + (aceCount - i) * 10)
            .filter(i -> i <= BLACKJACK_SCORE)
            .findFirst()
            .orElse(sum);
    }

    private int calculateMinimumSum() {
        return cards.stream()
            .mapToInt(Card::getValue)
            .sum();
    }

    private int calculateAceCount() {
        return (int)cards.stream()
            .filter(card -> card.isSameValueWith(Denomination.ACE))
            .count();
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACKJACK_COUNT && calculateScore() == BLACKJACK_SCORE;
    }

    abstract public boolean isHittable();

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public String getName() {
        return name.getValue();
    }
}
