package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;
import blackjack.domain.money.Money;

public class Player extends Participant {

    private static final String INVALID_NAME_LENGTH_EXCEPTION_MESSAGE = "이름은 1글자 이상이어야합니다.";
    private static final String NEGATIVE_BET_MONEY_EXCEPTION_MESSAGE = "베팅 금액은 음수일 수 없습니다.";

    private final Money betMoney;

    private Player(final String name, final Hand hand, final Money betMoney) {
        super(name, hand);
        this.betMoney = betMoney;
    }

    public static Player of(final String name, final Hand hand, final Money betMoney) {
        validateNameNotEmpty(name);
        validateBetMoney(betMoney);

        return new Player(name, hand, betMoney);
    }

    private static void validateNameNotEmpty(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH_EXCEPTION_MESSAGE);
        }
    }

    private static void validateBetMoney(Money betMoney) {
        if (betMoney.isNegative()) {
            throw new IllegalArgumentException(NEGATIVE_BET_MONEY_EXCEPTION_MESSAGE);
        }
    }

    public void receiveCard(Card card) {
        getHand().add(card);
    }

    public boolean canReceive() {
        Score score = Score.calculateSumFrom(getHand());
        return !score.isBusted();
    }

    public Money calculateProfit(Dealer dealer) {
        ResultType resultType = compareWith(dealer);
        return betMoney.calculateProfit(resultType);
    }

    public ResultType compareWith(Dealer other) {
        if (isBlackjack()) {
            return getResultTypeIfBlackjack(other);
        }

        if (isBusted()) {
            return ResultType.LOSE;
        }

        return getResultTypeIfOrdinary(other);
    }

    private ResultType getResultTypeIfBlackjack(Participant other) {
        if (other.isBlackjack()) {
            return ResultType.DRAW;
        }

        return ResultType.WIN_WITH_BLACKJACK;
    }

    private ResultType getResultTypeIfOrdinary(Participant other) {
        boolean isScoreGreaterThanOther = getCurrentScore().isGreaterThan(other.getCurrentScore());
        boolean isScoreLessThanOther = getCurrentScore().isLessThan(other.getCurrentScore());

        if (other.isBusted() || isScoreGreaterThanOther) {
            return ResultType.WIN;
        }
        if (other.isBlackjack() || isScoreLessThanOther) {
            return ResultType.LOSE;
        }
        return ResultType.DRAW;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + super.getName() +
                ", hand=" + super.getHand() +
                ", betMoney=" + betMoney +
                '}';
    }
}
