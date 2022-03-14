package blackJack.domain.card;

import static blackJack.domain.card.Denomination.ACE_BONUS_VALUE;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final String ERROR_MESSAGE_RECEIVE_DUPLICATED_CARD = "중복된 카드는 받을 수 없습니다.";

    private static final int BLACK_JACK = 21;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
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

    public List<Card> getCards() {
        return cards;
    }
}
