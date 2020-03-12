package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UserCards {
    private List<Card> cards;

    public UserCards(List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            throw new RuntimeException("카드가 없습니다");
        }
        this.cards = new LinkedList<>(cards);
    }

    public int getTotalScore() {
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        score = incrementAceScore(score);
        return score;
    }

    private int incrementAceScore(int score) {
        if (cards.stream().anyMatch(Card::isAce) && score <= 11) {
            score += 10;
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
}
