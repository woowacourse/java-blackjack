package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private static final int BUST_SCORE = 21;
    private final List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards of(Card... values) {
        List<Card> cards = Arrays.stream(values)
            .collect(Collectors.toList());
        return new Cards(cards);
    }

    public static Cards of(List<Card> values) {
        return new Cards(values);
    }

    public int calculateScore() {
        int result = 0;
        result += calculateScoreExceptAce();
        result = calculateTotalScoreWithAce(result);
        return result;
    }

    private int calculateTotalScoreWithAce(int result) {
        int sum = result;
        List<Card> aceCards = cards.stream()
                .filter(Card::isAce)
                .collect(Collectors.toList());
        for (Card card : aceCards) {
            sum += card.getAceScore(sum);
        }
        return sum;
    }

    private int calculateScoreExceptAce() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getScore)
                .sum();
    }

    public boolean isBust() {
        return calculateScore() > BUST_SCORE;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
