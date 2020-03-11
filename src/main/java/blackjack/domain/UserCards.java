package blackjack.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UserCards {
    private List<Card> cards;

    public UserCards(List<Card> cards) {
        if (cards == null || cards.size() == 0) {
            throw new RuntimeException("카드가 없습니다");
        }
        this.cards = new LinkedList<>(cards);
    }

    public int getTotalScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
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
