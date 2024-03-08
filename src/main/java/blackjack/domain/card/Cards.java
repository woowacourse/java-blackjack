package blackjack.domain.card;

import java.util.List;

public class Cards {

    private static final int BLACKJACK_CANDIDATE = 21;
    private static final int INITIAL_CARD_SIZE = 2;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public Card findFirst() {
        return cards.get(0);
    }

    public int sum() {
        final List<Number> numbers = cards.stream()
                .map(Card::number)
                .toList();

        if (numbers.contains(Number.ACE)) {
            return calculateOptimalSum(numbers);
        }
        return Number.sum(numbers);
    }

    private int calculateOptimalSum(final List<Number> numbers) {
        int softHandSum = Number.sumOneAceToSoftHand(numbers);

        if (softHandSum <= BLACKJACK_CANDIDATE) {
            return softHandSum;
        }
        return Number.sum(numbers);
    }

    public boolean hasOnlyInitialCard() {
        return cards.size() == INITIAL_CARD_SIZE;
    }

    public List<Card> getCards() {
        return cards;
    }
}
