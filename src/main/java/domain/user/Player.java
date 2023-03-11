package domain.user;

import domain.Money;
import domain.card.Card;
import domain.card.Hand;

import java.util.ArrayList;
import java.util.List;

public class Player extends User {
    private static final int PLAYER_HIT_LIMIT = 21;

    private final String name;
    private final Money money;

    public Player(String name, Money bettingAmount) {
        this(name, new ArrayList<>(), bettingAmount);
    }

    public Player(String name, List<Card> cards, Money bettingAmount) {
        super(new Hand(cards));
        this.name = name;
        this.money = bettingAmount;
    }

    @Override
    public boolean canHit() {
        return score().isLessThan(PLAYER_HIT_LIMIT);
    }

    @Override
    public String getName() {
        return name;
    }

    public Money getMoney() {
        return money;
    }
}


