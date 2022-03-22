package blackjack.domain;


import java.util.Objects;

public class BettingMoney {

    static final String NOT_ALLOW_UNIT_OF_THOUSAND_MESSAGE = "[ERROR] 1000원 단위의 숫자를 입력해야 합니다.";
    public static final int BETTING_AMOUNT_UNIT = 1_000;

    private String ownerName;
    private int value;

    public BettingMoney(String name, String inputMoney) {
        validateInput(inputMoney);
        this.ownerName = name;
        this.value = Integer.parseInt(inputMoney);
    }

    public BettingMoney(String name, int bettingMoney) {
        isUnitOfThousand(bettingMoney);
        this.ownerName = name;
        this.value = bettingMoney;
    }

    private void validateInput(String inputMoney) {
        int money = Integer.parseInt(inputMoney);
        isUnitOfThousand(money);
    }

    private void isUnitOfThousand(int money) {
        if (money < BETTING_AMOUNT_UNIT || Math.floorMod(money, BETTING_AMOUNT_UNIT) != 0) {
            throw new IllegalArgumentException(NOT_ALLOW_UNIT_OF_THOUSAND_MESSAGE);
        }
    }

    private void isNumber(String inputMoney) {

    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BettingMoney that = (BettingMoney) o;
        return value == that.value && Objects.equals(ownerName, that.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerName, value);
    }

    @Override
    public String toString() {
        return "BettingMoney{" +
                "ownerName='" + ownerName + '\'' +
                ", value=" + value +
                '}';
    }
}
