package blackjack.domain.participant;

import blackjack.domain.card.Cards;

public class Player extends Participant {

    private static final int HIT_STANDARD = 21;

    private final BetMoney betMoney;

    public Player(Name name, Cards cards, BetMoney betMoney) {
        super(name, cards);
        this.betMoney = betMoney;
    }

    public BetMoney getBetMoney() {
        return betMoney;
    }

    @Override
    public boolean isHittable() {
        return cards.isLessScoreThan(HIT_STANDARD);
    }
}
