package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.Objects;

public class UserCards {
    private static final String NULL_CARDS = "카드가 없습니다";
    private static final int INCREMENT_ACE_THRESHOLD = 11;
    private static final int ACE_INCREMENT = 10;
    private static final int BUST_BOUND = 21;
    private static final int RESET_VAL = 0;
    private static final int BUSTED_VAL_RESET = RESET_VAL;
    private List<Card> cards;

    public UserCards(List<Card> cards) {
        Objects.requireNonNull(cards, NULL_CARDS);
        this.cards = cards;
    }

    public int calculateTotalScore() {
        int score = sumUpScore();
        score = incrementAceScore(score);
        return resetScoreIfBusted(score);
    }

    private int sumUpScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int incrementAceScore(int score) {
        if (hasAce() && score <= INCREMENT_ACE_THRESHOLD) {
            score += ACE_INCREMENT;
        }
        return score;
    }

    private int resetScoreIfBusted(int score) {
        if (score > BUST_BOUND) {
            score = BUSTED_VAL_RESET;
        }
        return score;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCard(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Card> getCards(int amount) {
        return cards.subList(0, amount);
    }
}
