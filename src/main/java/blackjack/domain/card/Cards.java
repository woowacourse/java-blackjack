package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.smartcardio.CardChannel;

public class Cards {

    private static final int BUST = 21;
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
        result += calculateScoreWithOutAce();
        result += calculateAceScore(result);
        return result;
    }

    private int calculateScoreWithOutAce() {
        return cards.stream()
            .filter(card -> !card.isAce())
            .mapToInt(card -> card.getScore())
            .sum();
    }

    private int calculateAceScore(int result) {
        List<Card> aceCards = cards.stream()
            .filter(Card::isAce)
            .collect(Collectors.toList());
        int sum = 0;
        for (Card card : aceCards) {
            int aceScore = card.getAceScore(result);
            result += aceScore;
            sum += aceScore;
        }
        return sum;
    }

    public boolean isBust() {
        return calculateScore() > BUST;
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
