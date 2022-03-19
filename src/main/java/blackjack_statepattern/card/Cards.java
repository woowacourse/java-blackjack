package blackjack_statepattern.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_BONUS_SCORE = 10;

    private final List<Card> value;

    public Cards() {
        this(new ArrayList<>());
    }

    public Cards(final Card... value) {
        this(List.of(value));
    }

    public Cards(final List<Card> value) {
        this.value = value;
    }

    public int computeScore() {
        int sum = sum();
        if (hasAce() && sum <= BLACKJACK_SCORE - ACE_BONUS_SCORE) {
            sum += ACE_BONUS_SCORE;
        }
        return sum;
    }

    public int sum() {
        return value.stream()
                .mapToInt(Card::score)
                .sum();
    }

    public boolean hasAce() {
        return value.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        return BLACKJACK_SCORE - ACE_BONUS_SCORE == sum() && hasAce();
    }

    public Cards add(Card card) {
        final var newValue = new ArrayList<>(value);
        newValue.add(card);

        return new Cards(newValue);
    }

    public boolean isBust() {
        return sum() > BLACKJACK_SCORE;
    }

    public boolean isReady() {
        return value.size() < 2;
    }

    public List<Card> getCards() {
        return new ArrayList<>(value);
    }

    public Card getOneCard() {
        return value.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 카드가 한장도 없습니다."));
    }

    @Override
    public String toString() {
        return "Cards{" +
                "value=" + value +
                '}';
    }
}
