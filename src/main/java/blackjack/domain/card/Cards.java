package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Cards {

    private List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int totalScore = getTotalScore();
        int countOfAce = getCountOfAce();
        while (countOfAce-- > 0 && totalScore > 21) {
            totalScore -= 10;
        }
        return totalScore;
    }

    private int getTotalScore() {
        return cards.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::getValue)
                .sum();
    }

    private int getCountOfAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
