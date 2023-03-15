package blackjack.domain.card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static blackjack.common.BlackJackRule.INITIAL_CARD_SIZE;
import static blackjack.common.BlackJackRule.MAX_SCORE_NOT_BUST;

public class Cards {
    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        validate(cards);
        this.cards = new ArrayList<>(cards);
    }

    public void receive(final Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException("중복되는 카드를 가질 수 없습니다.");
        }
        cards.add(card);
    }

    public int calculate() {
        List<CardNumber> cardNumbers = cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.toList());
        return CardNumber.getMaxValueNearBlackJack(cardNumbers, MAX_SCORE_NOT_BUST.getValue());
    }

    public List<Card> open(int size) {
        return IntStream.range(0, size)
                .mapToObj(cards::get)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean isBust() {
        return calculate() > MAX_SCORE_NOT_BUST.getValue();
    }

    public boolean isBlackJack() {
        return cards.size() == INITIAL_CARD_SIZE.getValue() && calculate() == MAX_SCORE_NOT_BUST.getValue();
    }

    private void validate(final List<Card> cards) {
        if (cards.size() != INITIAL_CARD_SIZE.getValue()) {
            throw new IllegalArgumentException("첫 카드는 두 장이어야 합니다.");
        }

        if (new HashSet<>(cards).size() != INITIAL_CARD_SIZE.getValue()) {
            throw new IllegalArgumentException("카드는 중복될 수 없습니다.");
        }
    }

    public int getSize() {
        return cards.size();
    }
}
