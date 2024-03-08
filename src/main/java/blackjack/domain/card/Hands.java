package blackjack.domain.card;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Hands {
    private final List<Card> cards;

    public Hands(final List<Card> cards) {
        validateDuplicate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validateDuplicate(final List<Card> cards) {
        if (Set.copyOf(cards).size() != cards.size()) {
            throw new IllegalStateException("중복된 카드는 존재할 수 없습니다.");
        }
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public void addStartCard(final Card firstCard, final Card secondCard) {
        cards.add(firstCard);
        cards.add(secondCard);
    }

    public int sum() {
        return cards.stream()
                .mapToInt(Card::getRealNumber)
                .sum();
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public Hands getFirstCard() {
        return cards.stream()
                .limit(1)
                .collect(collectingAndThen(toList(), Hands::new));
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
