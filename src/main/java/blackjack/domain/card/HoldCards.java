package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;

public class HoldCards {
    private static final int FIRST_CARD = 0;

    private final List<Card> cards;

    private HoldCards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static HoldCards init(List<Card> cards) {
        validateDuplicate(cards);
        return new HoldCards(cards);
    }

    public void addCard(Card card) {
        validateDuplicate(card);
        cards.add(card);
    }

    public int countBestNumber() {
        return Number.sum(cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.toList()));
    }

    public Optional<Card> getFirstCard() {
        return Optional.ofNullable(cards.get(FIRST_CARD));
    }

    public List<Card> getCards() {
        return cards;
    }

    private static void validateDuplicate(List<Card> cards) {
        Set<Card> distinctCards = new HashSet<>(cards);
        if (distinctCards.size() != cards.size()) {
            throw new IllegalArgumentException("카드가 중복될 수 없습니다.");
        }
    }

    private void validateDuplicate(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException("카드가 중복될 수 없습니다.");
        }
    }
}
