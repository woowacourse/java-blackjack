package domain.participant;

import domain.money.Money;

import java.util.regex.Pattern;

import static util.BlackJackConstant.MAX_NAME_LENGTH;

public class Player extends Participant {

    private static final String STRING_REGEX = "^[a-zA-Z]*$";

    private final String name;
    private Money money = null;

    public Player(String name) {
        validateNameLength(name);
        validateOnlyEnglish(name);
        this.name = name;
    }

    public void bet(Integer money) {
        if (this.money != null) {
            throw new IllegalArgumentException("베팅 금액은 변경할 수 없습니다.");
        }

        this.money = new Money(money);
    }

    public String getName() {
        return name;
    }

    private void validateNameLength(String name) {
        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("플레이어 이름은 1글자 이상 8글자 이하여야 합니다.");
        }
    }

    private void validateOnlyEnglish(String name) {
        if (!Pattern.matches(STRING_REGEX, name)) {
            throw new IllegalArgumentException("플레이어 이름은 영문자만 포함되어야 합니다.");
        }
    }
}
