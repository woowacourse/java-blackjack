package blackjack.domain.card;

import static blackjack.domain.GameOutcome.DRAW;
import static blackjack.domain.GameOutcome.LOSE;
import static blackjack.domain.GameOutcome.WIN;

import blackjack.domain.GameOutcome;
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
        validateScoreSize();
        cards.add(card);
    }

    private void validateScoreSize() {
        if (calculateScore() > BLACK_JACK_NUMBER) {
            throw new IllegalStateException("21이 넘을때는 카드를 더 추가할 수 없습니다.");
        }
    }

    public boolean isBust() {
        return calculateScore() > BLACK_JACK_NUMBER;
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }

    public GameOutcome fightResult(final Cards compareCards) {
        if (this.isBust() || compareCards.isBust()) {
            return bustGameFightResult(compareCards);
        } else if (this.isBlackJack() || compareCards.isBlackJack()) {
            return blackJackGameFightResult(compareCards);
        }
        return GameOutcome.calculateOutcome(this.calculateScore(), compareCards.calculateScore());
    }

    private GameOutcome bustGameFightResult(final Cards compareCards) {
        if (this.isBust() && compareCards.isBust()) {
            return DRAW;
        } else if (this.isBust()) {
            return LOSE;
        }
        return WIN;
    }

    private GameOutcome blackJackGameFightResult(final Cards compareCards) {
        if (this.isBlackJack() && compareCards.isBlackJack()) {
            return DRAW;
        } else if (this.isBlackJack()) {
            return WIN;
        }
        return LOSE;
    }

    private boolean isBlackJack() {
        return cards.size() == BLACK_JACK_SIZE && calculateScore() == BLACK_JACK_NUMBER;
    }
}
