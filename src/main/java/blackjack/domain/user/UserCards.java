package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UserCards {
    public static final String NO_CARD = "카드가 없습니다";
    public static final int ACE_INCREMENT = 10;
    public static final int BLACKJACK = 21;
    private List<Card> cards;

    public UserCards(List<Card> cards) {
        validateNullOrEmptyCard(cards);
        this.cards = new LinkedList<>(cards);
    }

    private void validateNullOrEmptyCard(List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            throw new RuntimeException(NO_CARD);
        }
    }

    public int getTotalScore() {
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        score = incrementAceScore(score);
        return score;
    }

    private int incrementAceScore(int score) {
        if (cards.stream().anyMatch(Card::isAce) && score <= BLACKJACK - ACE_INCREMENT) {
            score += ACE_INCREMENT;
        }
        return score;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<String> getCardInfo() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.toList());
    }

    public boolean isBusted() {
        return getTotalScore() > BLACKJACK;
    }

    public boolean isBlackJack() {
        return cards.size() == 2
                && getTotalScore() == BLACKJACK;
    }
}
