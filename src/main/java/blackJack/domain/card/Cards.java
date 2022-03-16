package blackJack.domain.card;

import static blackJack.domain.card.Denomination.ACE_BONUS_VALUE;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Cards {

    private static final String ERROR_MESSAGE_RECEIVE_DUPLICATED_CARD = "중복된 카드는 받을 수 없습니다.";

    private static final int BLACK_JACK = 21;
    private static final int BLACK_JACK_COUNT = 2;

    private final Set<Card> cards;

    public Cards() {
        this(new HashSet<>());
    }

    public Cards(Set<Card> cards) {
        Objects.requireNonNull(cards, "카드들의 값이 null 입니다.");
        this.cards = new LinkedHashSet<>(cards);
    }

    public void addCard(Card card) {
        validateReceiveDuplicatedCard(card);
        cards.add(card);
    }

    private void validateReceiveDuplicatedCard(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_RECEIVE_DUPLICATED_CARD);
        }
    }

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_COUNT && addScore() == BLACK_JACK;
    }

    public int addScore() {
        int score = calculateScore();

        if (hasDenominationAce() && score + ACE_BONUS_VALUE <= BLACK_JACK) {
            score += ACE_BONUS_VALUE;
        }

        return score;
    }

    private int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasDenominationAce() {
        return cards.stream()
                .anyMatch(Card::isSameDenominationAsAce);
    }

    public Set<Card> getCards() {
        return cards;
    }
}
