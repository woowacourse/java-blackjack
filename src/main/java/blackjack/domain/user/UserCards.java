package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public class UserCards {
    private static final String NO_CARD = "카드가 없습니다";
    private static final int INCREMENT_ACE_THRESHOLD = 11;
    private static final int ACE_INCREMENT = 10;
    private static final int BUST_BOUND = 21;
    private static final int RESET_VAL = 0;
    private static final int BUSTED_VAL_RESET = RESET_VAL;
    private List<Card> cards;

    public UserCards(List<Card> cards) {
        validateNullOrEmptyCard(cards);
        this.cards = cards;
    }

    private void validateNullOrEmptyCard(List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            throw new RuntimeException(NO_CARD);
        }
    }

    public int calculateTotalScore() {
        int score = incrementAceScore(cards.stream()
                .mapToInt(Card::getScore)
                .sum());
        return resetScoreIfBusted(score);
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

    public List<String> getCardName() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.toList());
    }
}
