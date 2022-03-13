package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int MAX_SCORE = 21;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        validateInitCards(cards);
        this.cards = new ArrayList<>();
        addCards(cards);
    }

    private void validateInitCards(final List<Card> cards) {
        if (cards == null || cards.size() != Deck.INIT_DISTRIBUTE_SIZE) {
            throw new IllegalArgumentException("[ERROR] 잘못 배분된 카드입니다.");
        }
    }

    public void addCards(final List<Card> cards) {
        for (Card card : cards) {
            addCard(card);
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
            return calculateScoreByAceOne() + Score.getDifferenceAcesScore();
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

    public List<Card> getCards() {
        return List.copyOf(this.cards);
    }
}
