package blackjack.model.participant;

import blackjack.model.game.DrawStrategy;
import blackjack.model.game.GameSign;
import blackjack.model.game.TurnProgress;

public class Player extends Participant {
    private static final int PLAYER_HIT_LIMIT_SCORE = 20;

    private final int bet;

    public Player(final String name, final int bet) {
        super(name);
        checkNaturalNumber(bet);
        this.bet = bet;
    }

    private void checkNaturalNumber(int bet) {
        if (bet <= 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 0보다 큰 금액이여야 합니다.");
        }
    }

    public double getProfit(Participant dealer) {
        return bet * this.state
                .calculateBettingRate(dealer.state)
                .getBettingRate();
    }

    @Override
    public void hitFrom(DrawStrategy drawStrategy, GameSign gameSign, TurnProgress turnProgress) {
        while (this.state.isHitAble() && isNotStay(gameSign)) {
            this.state = this.state.add(drawStrategy.draw());
            turnProgress.show(this);
        }
    }

    private boolean isNotStay(final GameSign gameSign) {
        String sign = gameSign.choiceSignTo(name);
        if (sign.equals("y")) {
            return true;
        }
        if (sign.equals("n") || isFinished()) {
            this.state = state.stay();
            return false;
        }
        throw new IllegalArgumentException("[ERROR] y 또는 n만 입력해주세요");
    }

    private boolean isFinished() {
        return this.state.getScore() > PLAYER_HIT_LIMIT_SCORE;
    }
}
