package blackJack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int BLACK_JACK_CARD_COUNT = 2;
    private static final String ERROR_MESSAGE_RECEIVE_DUPLICATED_CARD = "중복된 카드는 받을 수 없습니다.";

    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public void receiveCard(Card card) {
        validateReceiveDuplicatedCard(card);
        cards.add(card);
    }

    private void validateReceiveDuplicatedCard(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_RECEIVE_DUPLICATED_CARD);
        }
    }

    public boolean isBlackJackPossibleCount() {
        return cards.size() == BLACK_JACK_CARD_COUNT;
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public List<Card> getCards() {
        return cards;
    }
}
