package blackjack.domain.participant;

import blackjack.domain.card.Cards;

public class Player extends Participant {

    private static final int HIT_STANDARD = 21;
    private static final int FIRST_OPEN_CARD_SIZE = 2;

    private final BetMoney betMoney;

    public Player(Name name, Cards cards, BetMoney betMoney) {
        super(name, cards);
        this.betMoney = betMoney;
    }

    public BetMoney getBetMoney() {
        return betMoney;
    }

    @Override
    protected int getHitStandard() {
        return HIT_STANDARD;
    }

    @Override
    protected int getFirstOpenCardSize() {
        return FIRST_OPEN_CARD_SIZE;
    }
}
