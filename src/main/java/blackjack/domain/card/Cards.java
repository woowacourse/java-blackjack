package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cards {
    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        validate(cards);
        this.cards = cards;
    }

    public void receive(final Card card) {
        validateReceive(card);
        cards.add(card);
    }

    public List<Card> open(final int openCount) {
        return IntStream.range(0, openCount)
                .mapToObj(cards::get)
                .collect(Collectors.toList());
    }

    public List<Card> openAll() {
        return new ArrayList<>(cards);
    }

    public List<CardNumber> getCardNumbers() {
        return cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.toList());
    }

    private static void validate(final List<Card> cards) {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드 리스트는 비어있을 수 없습니다.");
        }
    }

    private void validateReceive(final Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException("중복된 카드를 받을 수 없습니다.");
        }
    }
}
