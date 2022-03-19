package blackjack.domain.card;

import blackjack.domain.BlackJack;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int sum() {
        return calibratedScore(cards.stream()
            .mapToInt(Card::getValue)
            .sum());
    }

    private int calibratedScore(int sum) {
        if (hasAce() && !BlackJack.isOverMaxScore(sum + CardValue.ACE_CAlIBRATION_SCORE)) {
            return sum + CardValue.ACE_CAlIBRATION_SCORE;
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return cards;
    }
}
