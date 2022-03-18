package domain.participant;

import domain.card.Deck;
import utils.ExceptionMessages;

public final class Player extends Participant {

    private static final int MAX_SCORE = 21;
    private static final int COMPARE_CRITERIA = 0;

    private Money money;

    public Player(String name) {
        super(name);
    }

    public Result judgeResult(Dealer dealer) {
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
        this.cards = cards.addCard(deck.handOut());
    }

    @Override
    public boolean canHit() {
        return !isBlackJack() && cards.calculateSum() < MAX_SCORE;
    }

    public void betMoney(int money) {
        this.money = new Money(money);
    }

    public int calculateIncome(Result result) {
        return money.multiplyDividendRate(result.getDividendRate());
    }

}
