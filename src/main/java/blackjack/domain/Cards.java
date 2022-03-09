package blackjack.domain;

import static blackjack.domain.GameOutcome.DRAW;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cards {

    private static final int BLACK_JACK_NUMBER = 21;
    private static final int BLACK_JACK_SIZE = 2;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        Objects.requireNonNull(cards, "[Error] 카드에는 null이 들어올 수 없습니다.");
        this.cards = cards;
    }

    public int calculateScore() {
        final List<CardNumber> cardNumbers = cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.toUnmodifiableList());
        return CardNumber.calculateScore(cardNumbers);
    }

    public void addCard(final Card card) {
        validateScoreSize();
        cards.add(card);
    }

    private void validateScoreSize() {
        if (calculateScore() > BLACK_JACK_NUMBER) {
            throw new IllegalStateException("[Error] 21이 넘을때는 카드를 더 추가할 수 없습니다.");
        }
    }

    public boolean isBust() {
        return calculateScore() > BLACK_JACK_NUMBER;
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }

    public GameOutcome fightResult(final Cards compareCards) {
        if (this.isBlackJack() && compareCards.isBlackJack()) {
            return DRAW;
        }
        return null;
    }

    private boolean isBlackJack() {
        return cards.size() == BLACK_JACK_SIZE && calculateScore() == BLACK_JACK_NUMBER;
    }

}
