package blackjack.domain.participant;

import blackjack.domain.result.Result;
import blackjack.domain.result.ScoreCalculator;
import blackjack.domain.result.UserProfit;

public class User extends Participant {

    private static final String ERROR_INVALID_NAME = "[ERROR] 유저의 이름은 한 글자 이상이어야 합니다.";
    private static final int BUST_STANDARD = 21;
    private static final String ERROR_NEGATIVE_PRICE = "[ERROR] 금액으로 음수를 입력할 수 없습니다.";
    private static final int DEFAULT_BETTING_PRICE = 0;

    private final int bettingPrice;

    public User(String name) {
        this(name, DEFAULT_BETTING_PRICE);
    }

    public User(String name, int bettingPrice) {
        super(name);
        validateName(name);
        this.bettingPrice = bettingPrice;
        validatePrice(bettingPrice);
    }

    private void validatePrice(int bettingPrice) {
        if (bettingPrice < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_PRICE);
        }
    }

    private void validateName(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException(ERROR_INVALID_NAME);
        }
    }

    public Result checkResult(int otherScore) {
        return Result.checkUserResult(ScoreCalculator.cardSum(cards), otherScore);
    }

    public int calculateProfit(int otherScore) {
        return UserProfit.calculateMoney(checkResult(otherScore), checkBlackJack(), bettingPrice);
    }

    private boolean checkBlackJack() {
        return cards.size() == 2 && ScoreCalculator.cardSum(cards) == BUST_STANDARD;
    }

    public boolean checkBust() {
        return ScoreCalculator.cardSum(cards) > BUST_STANDARD;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public boolean isUser() {
        return true;
    }
}
