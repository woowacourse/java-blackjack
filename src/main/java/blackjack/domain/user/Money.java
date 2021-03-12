package blackjack.domain.user;

public class Money {
    private static final int MINIMUM_PRICE = 1000;
    private final double money;

    private Money(long money) {
        this.money = money;
    }

    public static Money from(String input) {
        validateMoney(input);
        validatePrice(input);
        return new Money(Long.parseLong(input));
    }

    private static void validateMoney(String input) {
        try {
            Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("잘못된 입력입니다.");
        }
    }

    private static void validatePrice(String input) {
        if (Long.parseLong(input) < MINIMUM_PRICE) {
            throw new IllegalArgumentException("배팅금액은 최소 1000원부터 입력 가능합니다.");
        }
    }

    public double toDouble() {
        return money;
    }
}
