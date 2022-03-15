package blackjack.domain.paticipant;

import java.util.Objects;

public class Player {

    private final String name;
    private final int betMoney;

    public Player(final String name, final int betMoney) {
        Objects.requireNonNull(name, "이름은 null이 들어올 수 없습니다.");
        checkEmptyName(name);
        checkNotPositiveBetMoney(betMoney);
        this.name = name;
        this.betMoney = betMoney;
    }

    private void checkEmptyName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 공백이 들어올 수 없습니다.");
        }
    }

    private void checkNotPositiveBetMoney(final int betMoney) {
        if (betMoney <= 0) {
            throw new IllegalArgumentException("배팅금액은 0이하의 값이 들어올 수 없습니다.");
        }
    }
}
