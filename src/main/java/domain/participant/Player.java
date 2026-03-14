package domain.participant;

import domain.money.Money;

import java.util.Optional;
import java.util.regex.Pattern;

import static util.BlackJackConstant.MAX_NAME_LENGTH;

public class Player extends Participant {

    private static final String STRING_REGEX = "^[a-zA-Z]*$";

    private final String name;
    private Optional<Money> money = Optional.empty();

    public Player(String name) {
        validateNameLength(name);
        validateOnlyEnglish(name);
        this.name = name;
    }

    public void bet(Integer money) {
        if (this.money.isPresent()) {
            throw new IllegalArgumentException("베팅 금액은 변경할 수 없습니다.");
        }
        this.money = Optional.of(new Money(money));
    }

    public String getName() {
        return name;
    }

    public Money getMoney() {
        return money.orElseThrow(() ->
                new IllegalStateException("베팅 금액이 설정되지 않았습니다."));
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
