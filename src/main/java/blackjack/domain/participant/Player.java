package blackjack.domain.participant;

import blackjack.domain.Money;
import blackjack.domain.card.Cards;

public class Player extends Participant {

    private static final int HIT_STANDARD = 21;

    private final Money betMoney;

    public Player(Name name, Cards cards, Money betMoney) {
        super(name, cards);
        this.betMoney = betMoney;
    }

    public Money getBetMoney() {
        return betMoney;
    }

    @Override
    public boolean isHittable() {
        return calculateScore() < HIT_STANDARD;
    }
}
