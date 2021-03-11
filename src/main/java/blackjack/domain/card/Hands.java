package blackjack.domain.card;

import blackjack.domain.Score;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hands {

    private static final int INITIAL_SIZE = 2;

    private final List<Card> cards;

    public Hands(final List<Card> cards) {
        if (cards.size() != INITIAL_SIZE) {
            throw new IllegalArgumentException("[ERROR] 초기 카드는 2장입니다.");
        }
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> toList() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> cardsOf(int number) {
        return IntStream.range(0, number)
                .mapToObj(cards::get)
                .collect(Collectors.toList());
    }

    public boolean isBlackjack() {
        return cards.size() == INITIAL_SIZE && isMaxScore();
    }

    public Score calculate() {
        return Score.of(cards);
    }

    public boolean isMaxScore() {
        return calculate().isMaxScore();
    }

    public boolean isUnderMaxScore() {
        return calculate().isUnderMaxScore();
    }
}
