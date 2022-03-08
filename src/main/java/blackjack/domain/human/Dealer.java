package blackjack.domain.human;

import blackjack.domain.Card;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Human {
    private final List<Card> cards;
    private final String name = "딜러";

    private Dealer() {
        this.cards = new ArrayList<>();
    }

    public static Dealer of() {
        return new Dealer();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }

//    @Override
//    public boolean isOneMoreCard() {
//        return false;
//    }
}
