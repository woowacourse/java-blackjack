package blackjack.domain.participant;

import blackjack.domain.result.UserProfit;
import blackjack.domain.result.UserResult;

public class User extends Participant {

    private static final String ERROR_INVALID_NAME = "[ERROR] 유저의 이름은 한 글자 이상이어야 합니다.";
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

    public UserResult checkResult(Participant dealer) {
        return UserResult.checkUserResult(this, dealer);
    }

    public int calculateProfit(Participant dealer) {
        return UserProfit.calculateMoney(checkResult(dealer), isBlackJack(), bettingPrice);
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
