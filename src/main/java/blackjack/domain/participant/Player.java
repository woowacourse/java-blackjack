package blackjack.domain.participant;

import blackjack.domain.machine.Betting;

public class Player extends Participant {
    private final Betting betting;

    public Player(String name, long bettingMoney) {
        super(name);
        betting = new Betting(bettingMoney);
    }

    @Override
    public boolean canDraw() {
        return !isBust() && !isBlackjack();
    }

    public int isWin(Participant participant) {
        return Integer.compare(score().getSum(), participant.score().getSum());
    }

    public Betting getBetting() {
        return betting;
    }
}
