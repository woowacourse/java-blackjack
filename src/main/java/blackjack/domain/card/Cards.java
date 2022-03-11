package blackjack.domain.card;

import blackjack.domain.Result;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int ACE_VALUE = 10;
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
        while (countOfAce-- > 0 && totalScore > Result.MAX_SCORE) {
            totalScore -= ACE_VALUE;
        }
        return totalScore;
    }

    private int getCountOfAce() {
        return (int) cards.stream()
                .filter(Card::isAceCard)
                .count();
    }

    private int getTotalScore() {
        return cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
