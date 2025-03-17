package domain.participant;

import domain.card.CardHand;
import domain.game.GamblingMoney;
import domain.game.Winning;

public class Player extends Gambler {

    private static final int TAKE_CARD_THRESHOLD = 21;
    private static final int NAME_LENGTH_THRESHOLD = 10;

    private final GamblingMoney betMoney;

    public Player(String name, CardHand cardHand, GamblingMoney betMoney) {
        super(name, cardHand);
        this.betMoney = betMoney;
        validateNotBlank(name);
        validateLength(name);
    }

    @Override
    public boolean canTakeMoreCard() {
        return calculateScore() < TAKE_CARD_THRESHOLD;
    }

    public GamblingMoney calculateProfit(Dealer dealer) {
        if (this.isBlackJack() && !dealer.isBlackJack()) {
            return betMoney.onceHalf();
        }

        Winning winning = dealer.judgeWinningForPlayer(this);
        return betMoney.calculateProfit(winning);
    }

    private void validateLength(String name) {
        if (name.length() > NAME_LENGTH_THRESHOLD) {
            throw new IllegalArgumentException("플레이어의 이름은 10자를 넘을 수 없습니다.");
        }
    }

    private void validateNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 비어있을 수 없습니다.");
        }
    }
}
