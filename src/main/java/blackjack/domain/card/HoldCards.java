package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class HoldCards {
    private static final int FIRST_CARD = 0;
    private static final int INIT_CARD_SIZE = 2;

    private final List<Card> cards;

    public HoldCards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        validateSize(cards);
        validateDuplicate(cards);
    }

    public static HoldCards initTwoCards(Card card, Card otherCard) {
        return new HoldCards(List.of(card, otherCard));
    }

    public void addCard(Card card) {
        validateDuplicate(card);
        cards.add(card);
    }

    public int countBestNumber() {
        return CardNumber.sum(cards.stream()
            .map(Card::getNumber)
            .collect(Collectors.toList()));
    }

    public Optional<Card> getFirstCard() {
        return Optional.ofNullable(cards.get(FIRST_CARD));
    }

    private void validateSize(List<Card> cards) {
        if (cards.size() != INIT_CARD_SIZE) {
            throw new IllegalArgumentException("초기 카드는 2장이어야 합니다.");
        }
    }

    private void validateDuplicate(List<Card> cards) {
        if (Set.copyOf(cards).size() != cards.size()) {
            throw new IllegalArgumentException("카드가 중복될 수 없습니다.");
        }
    }

    private void validateDuplicate(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException("카드가 중복될 수 없습니다.");
        }
    }

    public List<Card> getCards() {
        return cards;
    }
}
