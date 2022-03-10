package blackjack.domain.card;

import blackjack.domain.game.GameOutcome;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cards {

    public static final int BLACK_JACK_NUMBER = 21;
    private static final int BLACK_JACK_SIZE = 2;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        Objects.requireNonNull(cards, "카드에는 null이 들어올 수 없습니다.");
        this.cards = new ArrayList<>(cards);
    }

    public int calculateScore() {
        final List<CardNumber> cardNumbers = cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.toUnmodifiableList());
        return CardNumber.calculateScore(cardNumbers);
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return calculateScore() > BLACK_JACK_NUMBER;
    }

    public List<Card> values() {
        return List.copyOf(cards);
    }

    public List<Card> firstCard() {
        return List.copyOf(cards.subList(0, 1));
    }

    public GameOutcome isHigherThan(final Cards another) {
        return GameOutcome.calculateOutcome(this.calculateScore(), another.calculateScore());
    }

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_SIZE && calculateScore() == BLACK_JACK_NUMBER;
    }
}
