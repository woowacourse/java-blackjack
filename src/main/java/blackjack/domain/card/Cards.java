package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final int BLACKJACK = 21;
    private final int DIFFERENCE_POINT_OF_ACE = 10;
    private final int MINIMUM_ACE_AMOUNT = 1;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(List<Card> value) {
        this.cards = value;
    }

    public int sum() {
        int sumOfPoint = cards.stream()
            .mapToInt(Card::point)
            .sum();
        if(hasAce() && sumOfPoint <= 11){
            sumOfPoint += 10;
        }
        return sumOfPoint;
    }

    public boolean isBlackJack() {
        return 21 == sum() && size() == 2;
    }

    public boolean hasAce() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return sum() > 21;
    }

    public boolean isReady() {
        return cards.size() < 2;
    }

    public static Cards create() {
        return new Cards();
    }

    public Cards add(Card card) {
        final List<Card> newValue = new ArrayList<>(cards);
        newValue.add(card);
        return new Cards(newValue);
    }

    public int size() {
        return this.cards.size();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
