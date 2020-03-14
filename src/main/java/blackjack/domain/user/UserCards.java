package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.exception.NoCardException;

import java.util.List;
import java.util.stream.Collectors;

public class UserCards {
    private static final String NO_CARD = "카드가 없습니다";
    private static final int ACE_INCREMENT = 10;
    private static final int MAX_SCORE = 21;
    private final List<Card> cards;

    public UserCards(List<Card> cards) {
        validateNullOrEmptyCard(cards);
        this.cards = cards;
    }

    private void validateNullOrEmptyCard(List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            throw new NoCardException(NO_CARD);
        }
    }

    public int getTotalScore() {
        int score = incrementAceScore(cards.stream()
                .mapToInt(Card::getScore)
                .sum());
        if(score > MAX_SCORE) {
            return 0;
        }
        return score;
    }

    private int incrementAceScore(int score) {
        if (cards.stream().anyMatch(Card::isAce) && score <= MAX_SCORE - ACE_INCREMENT) {
            score += ACE_INCREMENT;
        }
        return score;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void add(List<Card> newCards) {
        cards.addAll(newCards);
    }

    public List<String> getCardInfo() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.toList());
    }

    public boolean isBusted() {
        return getTotalScore() == 0;
    }

    public boolean isBlackJack() {
        return cards.size() == 2
                && getTotalScore() == MAX_SCORE;
    }
}
