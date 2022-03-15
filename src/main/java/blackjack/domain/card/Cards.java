package blackjack.domain.card;

import blackjack.domain.result.Judge;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int ANOTHER_ACE_SCORE = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(final Card card) {
        validateCard(card);
        cards.add(card);
    }

    private void validateCard(final Card card) {
        if (card == null) {
            throw new IllegalArgumentException("[ERROR] 올바른 카드를 입력해주세요.");
        }
    }

    public int calculateFinalScore() {
        final int score = calculateScoreByAceEleven();
        if (score <= Judge.MAX_SCORE) {
            return score;
        }
        return calculateScore();
    }

    public int calculateScoreByAceEleven() {
        if (containsAce()) {
            return calculateScore() + ANOTHER_ACE_SCORE;
        }
        return calculateScore();
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(card -> card.getScore().getAmount())
                .sum();
    }

    public List<Card> getCards() {
        return List.copyOf(this.cards);
    }
}
