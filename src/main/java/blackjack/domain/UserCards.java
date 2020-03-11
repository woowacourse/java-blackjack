package blackjack.domain;

import java.util.List;

public class UserCards {
    private List<Card> cards;

    public UserCards(List<Card> cards) {
        if (cards == null || cards.size() == 0) {
            throw new RuntimeException("카드가 없습니다");
        }
        this.cards = cards;
    }

    public int getTotalScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }
}
