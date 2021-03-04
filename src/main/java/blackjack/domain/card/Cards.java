package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

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
        List<Card> aceCards = new ArrayList<>();
        for (Card card : cards) {
            if (card.isAce()) {
                aceCards.add(card);
            } else {
                result += card.getScore();
            }
        }

        for (Card card : aceCards) {
            if (result < 11) {
                result += 11;
            } else {
                result += card.getScore();
            }
        }
        return result;
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
