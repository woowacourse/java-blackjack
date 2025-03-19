package blackjack.model.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public final class BlackJackCards {

    private final List<BlackJackCard> values;

    public BlackJackCards(final List<BlackJackCard> values) {
        this.values = new ArrayList<>(values);
    }

    public BlackJackCards(final BlackJackCard... blackJackCards) {
        this(
                new ArrayList<>(
                        Arrays.stream(blackJackCards)
                                .toList()
                )
        );
    }

    public static BlackJackCards empty() {
        return new BlackJackCards(Collections.emptyList());
    }

    public List<Integer> calculatePossiblePoints() {
        List<Integer> possiblePoints = new ArrayList<>();
        int minimumPoint = sumAll();
        int aceCount = Math.toIntExact(countByCardNumber(CardNumber.ACE));

        possiblePoints.add(minimumPoint);
        possiblePoints.addAll(calculateAceAppliedPoints(minimumPoint, aceCount));

        return possiblePoints;
    }

    private int sumAll() {
        return values.stream()
                .mapToInt(BlackJackCard::getValue)
                .sum();
    }

    private long countByCardNumber(final CardNumber cardNumber) {
        return values.stream()
                .filter(card -> card.cardNumber() == cardNumber)
                .count();
    }

    private List<Integer> calculateAceAppliedPoints(final int minimumPoint, final int aceCount) {
        return IntStream.rangeClosed(1, aceCount)
                .map(i -> minimumPoint + (i * CardNumber.ACE_ADDITIONAL_VALUE))
                .boxed()
                .toList();
    }

    public boolean hasSize(final int size) {
        return values.size() == size;
    }

    public void addAll(final BlackJackCards otherBlackJackCards) {
        this.values.addAll(otherBlackJackCards.values);
    }

    public List<BlackJackCard> pick(final int size) {
        validatePickSize(size);
        List<BlackJackCard> pickedBlackJackCards = List.copyOf(values.subList(0, size));
        values.removeAll(pickedBlackJackCards);
        return pickedBlackJackCards;
    }

    private void validatePickSize(final int size) {
        if (values.size() < size) {
            throw new IllegalArgumentException("남은 카드가 부족합니다.");
        }
    }

    public BlackJackCard getFirst() {
        return values.getFirst();
    }

    public List<BlackJackCard> getValues() {
        return List.copyOf(values);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlackJackCards blackJackCards)) {
            return false;
        }
        return Objects.equals(getValues(), blackJackCards.getValues());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValues());
    }

}
