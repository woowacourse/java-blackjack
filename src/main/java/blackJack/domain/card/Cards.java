package blackJack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private static final String ERROR_MESSAGE_HIT_DUPLICATED_CARD = "중복된 카드는 받을 수 없습니다.";

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        validateHitDuplicatedCard(card);
        cards.add(card);
    }

    private void validateHitDuplicatedCard(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_HIT_DUPLICATED_CARD);
        }
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public boolean containsAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<String> getCardsInfo() {
        return cards.stream()
                .map(Card::getCardInfo)
                .collect(Collectors.toUnmodifiableList());
    }
}
