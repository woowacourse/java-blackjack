package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.money.Money;

import java.util.List;

public class Player extends Gamer {

    private final Money money;

    public Player(String name, Money money) {
        super(name);
        this.money = money;
    }

    @Override
    public List<Card> showOpenHands2() {
        return state.cards().toList();
    }

//    @Override
//    public List<Card> showOpenHands() {
//        return hands.getCardsWithSize(2);
//    }
}
