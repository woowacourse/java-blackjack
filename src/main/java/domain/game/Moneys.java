package domain.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Moneys {
    private static final String ERROR_PARAM_MESSAGE = "잘못된 값이 전달되었습니다.";

    private final Map<String, Money> moneys;

    public Moneys() {
        moneys = new HashMap<>();
    }

    public void add(final String name, final Money money) {
        validateName(name);
        validateMoney(money);
        moneys.put(name, money);
    }

    private void validateName(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException(ERROR_PARAM_MESSAGE);
        }
    }

    private void validateMoney(Money money) {
        if (Objects.isNull(money) || money.getValue() <= 0) {
            throw new IllegalArgumentException(ERROR_PARAM_MESSAGE);
        }
    }

    public Money getValue(String name) {
        validateFindName(name);
        return moneys.get(name);
    }

    private void validateFindName(String name) {
        validateName(name);
        if (!moneys.containsKey(name)) {
            throw new IllegalArgumentException(ERROR_PARAM_MESSAGE);
        }
    }
}
