package blackjack.domain.card;

import blackjack.domain.game.Score;
import java.util.HashSet;
import java.util.Set;

public class CardBundle {
    private static final String NO_DUPLICATE_CARD_EXCEPTION_MESSAGE = "중복된 카드는 존재할 수 없습니다.";

    private final Set<Card> cards;

    private CardBundle(Set<Card> cards) {
        this.cards = cards;
    }

    public static CardBundle of(Card card1, Card card2) {
        Set<Card> initialCards = new HashSet<>(Set.of(card1, card2));
        return new CardBundle(initialCards);
    }

    public void add(Card card) {
        validateNoDuplicate(card);
        cards.add(card);
    }

    private void validateNoDuplicate(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(NO_DUPLICATE_CARD_EXCEPTION_MESSAGE);
        }
    }

    public Set<Card> getCards() {
        return cards;
    }

    public Score getScore() {
        return cards.stream()
                .map(Card::getRankValue)
                .reduce(Score.valueOf(0), Score::add);
    }

    @Override
    public String toString() {
        return "CardBundle{" + "cards=" + cards + '}';
    }
}
