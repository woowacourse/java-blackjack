package domain.participant;

import domain.card.Deck;
import utils.ExceptionMessages;

public final class Player extends Participant {

    private static final int MAX_SCORE = 21;

    private final BettingMoney money;

    private Player(String name, int money) {
        super(name);
        this.money = BettingMoney.from(money);
    }

    public static Player of(String name, int money) {
        return new Player(name, money);
    }

    public static Player of(String name, String money){
        return of(name, Integer.parseInt(money));
    }

    public Result receiveResult(Dealer dealer) {
        if (isBlackJack() && !dealer.isBlackJack()) {
            return Result.BLACKJACK;
        }
        if (!isBlackJack() && dealer.isBlackJack() || isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust()) {
            return Result.WIN;
        }

        return Result.judgeResult(calculateScore(), dealer.calculateScore());
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
        return money.multiplyDividendRate(result.getDividendRate());
    }

}
