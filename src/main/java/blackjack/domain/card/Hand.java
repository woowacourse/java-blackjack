package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int BLACKJACK_CANDIDATE = 21;
    private static final int VALUE_FOR_SOFT_HAND = 10;
    private static final int INITIAL_CARD_SIZE = 2;

    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public Hand() {
        this(new ArrayList<>());
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int calculateOptimalSum() {
        final List<Number> numbers = cards.stream()
                .map(Card::getNumber)
                .toList();

        if (numbers.contains(Number.ACE)) {
            return calculateAceToOptimal(numbers);
        }
        return sumHardHand(numbers);
    }

    private int calculateAceToOptimal(final List<Number> numbers) {
        final int hardHandScore = sumHardHand(numbers);
        final int softHandScore = hardHandScore + VALUE_FOR_SOFT_HAND;
        if (softHandScore <= BLACKJACK_CANDIDATE) {
            return softHandScore;
        }
        return hardHandScore;
    }

    private int sumHardHand(final List<Number> numbers) {
        return numbers.stream()
                .mapToInt(Number::getValue)
                .sum();
    }

    public boolean hasOnlyInitialCard() {
        return cards.size() == INITIAL_CARD_SIZE;
    }

    public Card findFirst() {
        return cards.get(0);
    }

    public List<Card> getCards() {
        return cards;
    }
}
