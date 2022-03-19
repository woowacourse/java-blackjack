package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;

public class HoldCards {
    private static final int FIRST_CARD = 0;
    public static final int INIT_CARD_SIZE = 2;

    private final List<Card> cards;

    private HoldCards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static HoldCards init(List<Card> cards) {
        validateDuplicate(cards);
        validateInitCardSize(cards);
        return new HoldCards(cards);
    }

    public void addCard(Card card) {
        validateDuplicate(card);
        cards.add(card);
    }

    public int countBestSum() {
        return Denomination.sum(cards.stream()
                .map(Card::getDenomination)
                .collect(Collectors.toList()));
    }

    public Optional<Card> getFirstCard() {
        return Optional.ofNullable(cards.get(FIRST_CARD));
    }

    private static void validateDuplicate(List<Card> cards) {
        Set<Card> distinctCards = new HashSet<>(cards);
        if (distinctCards.size() != cards.size()) {
            throw new IllegalArgumentException("카드가 중복될 수 없습니다.");
        }
    }

    private static void validateInitCardSize(List<Card> cards) {
        if (cards.size() != INIT_CARD_SIZE) {
            throw new IllegalArgumentException("초기 카드는 " + INIT_CARD_SIZE + "장씩 나눠져야 합니다.");
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
