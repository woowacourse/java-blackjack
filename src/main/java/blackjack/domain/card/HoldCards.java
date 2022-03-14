package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HoldCards {
    private static final int FIRST_CARD = 0;

    private final List<Card> cards;

    private HoldCards(Card first, Card second) {
        validateDuplicate(first, second);
        this.cards = new ArrayList<>();
        this.cards.add(first);
        this.cards.add(second);
    }

    public static HoldCards initTwoCards(Card card, Card otherCard) {
        return new HoldCards(card, otherCard);
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

    private void validateDuplicate(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException("카드가 중복될 수 없습니다.");
        }
    }

    private void validateDuplicate(Card first, Card second) {
        if (first.equals(second)) {
            throw new IllegalArgumentException("카드가 중복될 수 없습니다.");
        }
    }

    public List<Card> getCards() {
        return cards;
    }
}
