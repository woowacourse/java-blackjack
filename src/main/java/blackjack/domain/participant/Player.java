package blackjack.domain.participant;

import blackjack.domain.card.Card;

public class Player extends Participant{
    private final int battingMoney;

    public Player(String name, int money) {
        super(name);
        battingMoney = money;
    }
}
