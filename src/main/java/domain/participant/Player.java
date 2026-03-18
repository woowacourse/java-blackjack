package domain.participant;

import domain.money.Money;

import java.util.regex.Pattern;

import static util.BlackJackConstant.MAX_NAME_LENGTH;

public class Player extends Participant {

    private static final String STRING_REGEX = "^[a-zA-Z]*$";

    private final String name;
    private final Money money;

    public Player(String name, Integer money) {
        validateNameLength(name);
        validateNameEng(name);
        this.name = name;
        this.money = new Money(money);
    }

    public String getName() {
        return name;
    }

    public Money getMoney() {
        return money;
    }

    private void validateNameLength(String name) {
        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH)  {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요.");
        }
    }

    private void validateNameEng(String name) {
        if (!Pattern.matches(STRING_REGEX, name)) {
            throw new IllegalArgumentException("플레이어 이름은 영문자만 포함되어야 합니다.");
        }
    }
}
