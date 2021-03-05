package blackjack.domain.card;

import blackjack.domain.card.painting.Value;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Cards extends ArrayList<Card> {
    private static final int BLACK_JACK = 21;

    public Cards(Collection<Card> cards) {
        this.addAll(cards);
    }

    public int calculateScore() {
        int score = sumScore();

        while (score > BLACK_JACK && hasAce()) {
            OptionalInt indexOfAce = getIndexOfAce();
            indexOfAce.ifPresent(i -> this.set(i, new Card(this.get(i).getSuit(), Value.ACE_OF_ONE)));
            score = sumScore();
        }

        return score;
    }

    private int sumScore() {
        return this.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce() {
        return this.stream()
                .map(Card::getValue)
                .anyMatch(value -> value == Value.ACE);
    }

    private OptionalInt getIndexOfAce() {
        return IntStream.range(0, this.size())
                .filter(i -> this.get(i).isAce())
                .findFirst();
    }

}
