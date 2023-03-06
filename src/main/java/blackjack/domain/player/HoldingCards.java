package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class HoldingCards {

    private static final int MAXIMUM_SUM = 21;
    private static final int ACE_PLUS_POINT = 10;

    private final List<Card> cards = new ArrayList<>();

    public void initialCard(final Card firstCard, final Card secondCard) {
        cards.addAll(List.of(firstCard, secondCard));
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public List<Integer> getSums() {
        int defaultSum = getNotAceSum() + getAceCount();
        List<Integer> possibility = new ArrayList<>();
        possibility.add(defaultSum);
        for (int i = 1; i <= getAceCount(); i++) {
            possibility.add(defaultSum + ACE_PLUS_POINT * i);
        }
        return possibility;
    }

    private int getNotAceSum() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getPoint)
                .sum();
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public int getSum() {
        return getSums().stream()
                .filter(sum -> sum < MAXIMUM_SUM)
                .mapToInt(sum -> sum)
                .max()
                .orElse(getMinimumSum());
    }

    private int getMinimumSum() {
        return getSums().get(0);
    }
}
