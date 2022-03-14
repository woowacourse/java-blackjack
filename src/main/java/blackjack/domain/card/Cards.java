package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cards {

    private static final int MAX_SCORE = 21;
    private static final int ANOTHER_ACE_SCORE = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    private void validateInitCards(final List<Card> cards) {
        if (cards == null || cards.size() != Deck.INIT_DISTRIBUTE_SIZE) {
            throw new IllegalArgumentException("[ERROR] 잘못 배분된 카드입니다.");
        }
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
        if (score <= MAX_SCORE) {
            return score;
        }
        return calculateScoreByAceOne();
    }

    public int calculateScoreByAceEleven() {
        if (containsAce()) {
            return calculateScoreByAceOne() + ANOTHER_ACE_SCORE;
        }
        return calculateScoreByAceOne();
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public int calculateScoreByAceOne() {
        return cards.stream()
                .mapToInt(card -> card.getScore().getAmount())
                .sum();
    }

    public static int getMaxScore() {
        return MAX_SCORE;
    }

    public List<Card> getCards() {
        return List.copyOf(this.cards);
    }
}
