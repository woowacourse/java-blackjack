package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

import static blackjack.domain.card.painting.Value.ACE;

public class Cards extends ArrayList<Card> {
    private static final int BLACK_JACK = 21;

    public Cards(Collection<Card> cards) {
        this.addAll(cards);
    }

    public int calculateScore() {
        int score = sumScore();

        while (score > BLACK_JACK && hasAce()) {
            getAce().ifPresent(changeAceToAceOfOne());
            score = sumScore();
        }

        return score;
    }

    private Consumer<Card> changeAceToAceOfOne() {
        return card -> this.set(indexOf(card), card.toAceOfOne());
    }

    private int sumScore() {
        return this.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce() {
        return this.stream()
                .anyMatch(value -> value.isSameValue(ACE));
    }

    private Optional<Card> getAce() {
        return this.stream()
                .filter(Card::isAce)
                .findFirst();
    }
}
