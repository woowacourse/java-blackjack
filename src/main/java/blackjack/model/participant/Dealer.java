package blackjack.model.participant;

import blackjack.model.card.CardDeck;
import blackjack.model.game.DrawStrategy;
import blackjack.model.game.GameSign;
import blackjack.model.game.TurnProgress;

public class Dealer extends Participant {
    private static final String NAME = "딜러";
    private static final int DEALER_HIT_LIMIT_SCORE = 16;

    public Dealer() {
        super(NAME);
    }

    @Override
    public void hitFrom(DrawStrategy drawStrategy, GameSign gameSign, TurnProgress turnProgress) {
        while (this.state.isHitAble() && isNotStay()) {
            this.state = this.state.add(drawStrategy.draw());
        }
        turnProgress.show(this);
    }

    @Override
    public double getProfit(Participant otherParticipant) {
        throw new IllegalArgumentException("[ERROR] 딜러는 베팅 금액을 가지고 있지 않습니다.");
    }

    private boolean isNotStay() {
        if (isFinished()) {
            this.state = this.state.stay();
            return false;
        }
        return true;
    }

    private boolean isFinished() {
        return this.state.getScore() > DEALER_HIT_LIMIT_SCORE;
    }
}
