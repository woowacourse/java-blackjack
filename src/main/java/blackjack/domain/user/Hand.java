package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Value;

import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Hand {
    private static final int BLACK_JACK = 21;
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int calculateScore() {
        int score = sumScore();

        while (score > BLACK_JACK && hasAce()) {
            OptionalInt indexOfAce = getIndexOfAce();
            indexOfAce.ifPresent(i -> cards.set(i, new Card(cards.get(i).getSuit(), Value.ACE_OF_ONE)));
            score = sumScore();
        }

        return score;
    }

    private OptionalInt getIndexOfAce() {
        return IntStream.range(0, cards.size())
                .filter(i -> isAce(cards.get(i)))
                .findFirst();
    }

    private boolean hasAce(){
        return cards.stream()
                .map(Card::getValue)
                .anyMatch(value -> value == Value.ACE);
    }

    private boolean isAce(Card card) {
        return card.getValue() == Value.ACE;
    }

    private int sumScore() {
        return cards.stream()
                .map(Card::getValue)
                .mapToInt(Value::getScore)
                .sum();
    }
}
