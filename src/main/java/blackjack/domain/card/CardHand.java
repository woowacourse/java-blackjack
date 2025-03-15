package blackjack.domain.card;

import blackjack.domain.Score;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CardHand {
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public boolean isBlackjack() {
        return getScore().isBlackjack(deckSize());
    }

    public boolean isBust() {
        return getScore().isBust();
    }

    public Score getScore() {
        return new Score(calculatePossibleSum());
    }

    private Set<Integer> calculatePossibleSum() {
        List<Integer> sums = new ArrayList<>();
        sums.add(0);

        for (Card card : cards) {
            sums = sums.stream()
                    .flatMap(sum -> card.checkScore().stream().map(value -> sum + value))
                    .toList();
        }
        return new HashSet<>(sums);
    }

    public int deckSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
