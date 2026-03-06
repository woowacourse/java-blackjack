package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private final List<Card> cards;
    private final PlayerName name;

    public Dealer() {
        this.cards = new ArrayList<>();
        this.name = new PlayerName("딜러");
    }

    public void bring(Card card) {
        cards.add(card);
    }

    public List<String> getCardsName() {
        List<String> cardsName = new ArrayList<>();
        for (Card card : cards) {
            cardsName.add(card.getName());
        }

        return cardsName;
    }

    public String getName() {
        return name.name();
    }

    public int calculateCardsValue() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getValue();
        }
        return sum;
    }

    public boolean isBurst() {
        return calculateCardsValue() > 21;
    }

}
