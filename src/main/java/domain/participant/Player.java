package domain.participant;

import domain.card.Deck;
import utils.ExceptionMessages;

public final class Player extends Participant {

    private static final int MAX_SCORE = 21;

    private final BettingMoney money;

    public Player(Name name, String money) {
        super(name);
        this.money = new BettingMoney(Integer.parseInt(money));
    }

    public Result receiveResult(Dealer dealer) {
        return Result.judgeResult(this, dealer);
    }

    @Override
    public void hit(Deck deck) {
        if (!canHit()) {
            throw new IllegalStateException(ExceptionMessages.PLAYER_CAN_NOT_HIT_ERROR);
        }
        cards.addCard(deck.handOut());
    }

    @Override
    public boolean canHit() {
        return !isBlackJack() && cards.calculateSum() < MAX_SCORE;
    }

    public int calculateIncome(Result result) {
        return money.multiplyDividendRate(result.getProfitRate());
    }
}