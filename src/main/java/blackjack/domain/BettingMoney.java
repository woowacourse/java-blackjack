package blackjack.domain;

import java.util.Objects;

public class BettingMoney {

    static final String NOT_ALLOW_NULL_MESSAGE = "[ERROR] null 을 허용하지 않습니다.";
    public static final String NOT_ALLOW_BLANK_MESSAGE = "[ERROR] 공백을 허용하지 않습니다.";
    public static final String NOT_ALLOW_NUMBER_MESSAGE = "[ERROR] 숫자가 아닙니다.";
    public static final String NOT_ALLOW_UNIT_OF_THOUSAND_MESSAGE = "[ERROR] 1000원 단위의 숫자를 입력해야 합니다.";
    private String ownerName;
    private int value;

    public BettingMoney(String name, String inputMoney) {
        this.ownerName = name;
        validate(inputMoney);
    }

    private void validate(String inputMoney) {
        Objects.requireNonNull(inputMoney, NOT_ALLOW_NULL_MESSAGE);
        if (inputMoney.isBlank()) {
            throw new IllegalArgumentException(NOT_ALLOW_BLANK_MESSAGE);
        }
        int money = 0;
        try {
            money = Integer.parseInt(inputMoney);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(NOT_ALLOW_NUMBER_MESSAGE);
        }
        if (money < 1000 || Math.floorMod(money, 1_000) != 0) {
            throw new IllegalArgumentException(NOT_ALLOW_UNIT_OF_THOUSAND_MESSAGE);
        }
    }
}
