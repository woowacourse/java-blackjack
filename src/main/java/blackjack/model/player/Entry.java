package blackjack.model.player;

import blackjack.model.bet.Bet;
import blackjack.model.bet.Profit;
import blackjack.model.bet.Result;
import java.util.regex.Pattern;

public final class Entry extends Player {
    private static final String ERROR_BLANK = "[ERROR] 이름은 공백일 수 없습니다.";
    private static final String ERROR_MAX_LENGTH = "[ERROR] 이름은 15자 이하로 입력해주세요.";
    private static final String ERROR_CONTAINS_NUMBER = "[ERROR] 이름에 숫자는 포함될 수 없습니다.";
    private static final String ERROR_CONTAINS_SIGN = "[ERROR] 이름에 기호는 포함될 수 없습니다.";
    private static final String ERROR_ALREADY_BETTED = "[ERROR] 이미 배팅하였습니다.";

    private static final String REGEX_NAME_CONTAINS_NUMBER = "^\\D*[0-9]+\\D*$";
    private static final Pattern PATTERN_NAME_CONTAINS_NUMBER = Pattern.compile(REGEX_NAME_CONTAINS_NUMBER);
    private static final String REGEX_NAME_CONTAINS_SIGN = "^\\D*[!\"#$%&'()*+,./:;<=>?@\\\\^_`{|}~-]+\\D*$";
    private static final Pattern PATTERN_NAME_CONTAINS_SIGN = Pattern.compile(REGEX_NAME_CONTAINS_SIGN);

    private static final int MAX_LENGTH = 15;

    private Bet bet;

    public Entry(String name) {
        super(name);
        validate(name);
    }

    private void validate(String name) {
        checkBlank(name);
        checkLength(name);
        checkNumberIn(name);
        checkSignIn(name);
    }

    private void checkBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ERROR_BLANK);
        }
    }

    private void checkLength(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(ERROR_MAX_LENGTH);
        }
    }

    private void checkNumberIn(String name) {
        if (PATTERN_NAME_CONTAINS_NUMBER.matcher(name).matches()) {
            throw new IllegalArgumentException(ERROR_CONTAINS_NUMBER);
        }
    }

    private void checkSignIn(String name) {
        if (PATTERN_NAME_CONTAINS_SIGN.matcher(name).matches()) {
            throw new IllegalArgumentException(ERROR_CONTAINS_SIGN);
        }
    }

    public void bet(Bet bet) {
        if (this.bet != null) {
            throw new IllegalArgumentException(ERROR_ALREADY_BETTED);
        }
        this.bet = bet;
    }

    public Profit winProfit(Result result) {
        return result.apply(this.bet);
    }
}
