package blackjack;


import java.util.List;

public class Cards {
    List<CardValue> cardValues;

    public Cards(List<CardValue> cardValues) {
        this.cardValues = cardValues;
    }

    public int sum() {
        return cardValues.stream()
                         .mapToInt(CardValue::getValue)
                         .sum();
    }

    public boolean containAce() {
        return cardValues.stream()
                         .anyMatch(CardValue::isAce);
    }
}
