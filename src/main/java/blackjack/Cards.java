package blackjack;


import java.util.List;

public class Cards {
    List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int sum() {
        return cards.stream()
                    .mapToInt(Card::getValue)
                    .sum();
    }
}
