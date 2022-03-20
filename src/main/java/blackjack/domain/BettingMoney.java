package blackjack.domain;

import java.util.Objects;

public class BettingMoney {

    static final String NOT_ALLOW_NULL_MESSAGE = "[ERROR] null 을 허용하지 않습니다.";
    static final String NOT_ALLOW_BLANK_MESSAGE = "[ERROR] 공백을 허용하지 않습니다.";
    static final String NOT_ALLOW_NUMBER_MESSAGE = "[ERROR] 숫자가 아닙니다.";
    static final String NOT_ALLOW_UNIT_OF_THOUSAND_MESSAGE = "[ERROR] 1000원 단위의 숫자를 입력해야 합니다.";
    public static final int BETTING_AMOUNT_UNIT = 1_000;

    private String ownerName;
    private int value;

    public BettingMoney(String name, String inputMoney) {
        validateInput(inputMoney);
        this.ownerName = name;
        this.value = Integer.parseInt(inputMoney);
    }

    private void validateInput(String inputMoney) {
        Objects.requireNonNull(inputMoney, NOT_ALLOW_NULL_MESSAGE);
        isBlank(inputMoney);
        isNumber(inputMoney);
        int money = Integer.parseInt(inputMoney);
        isUnitOfThousand(money);
    }

    private void isUnitOfThousand(int money) {
        if (money < BETTING_AMOUNT_UNIT || Math.floorMod(money, BETTING_AMOUNT_UNIT) != 0) {
            throw new IllegalArgumentException(NOT_ALLOW_UNIT_OF_THOUSAND_MESSAGE);
        }
    }

    private void isNumber(String inputMoney) {
        try {
            Integer.parseInt(inputMoney);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(NOT_ALLOW_NUMBER_MESSAGE);
        }
    }

    private void isBlank(String inputMoney) {
        if (inputMoney.isBlank()) {
            throw new IllegalArgumentException(NOT_ALLOW_BLANK_MESSAGE);
        }
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getValue() {
        return value;
    }
}
