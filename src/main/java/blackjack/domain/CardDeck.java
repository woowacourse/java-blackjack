package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CardDeck {
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    // TODO start: 카드 덱 - 가능한 점수 합산, 덱 사이즈, 카드 목록 제공

    public Set<Integer> calculatePossibleSum() {
        List<Integer> sums = new ArrayList<>();
        sums.add(0);

        for (Card card : cards) {
            sums = sums.stream()
                    .flatMap(sum -> card.checkScore().stream().map(value -> sum + value))
                    .toList();
        }
        return new HashSet<>(sums);
    }

    public int getDeckSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
