package domain.participant;

import java.util.Objects;

public class ParticipantMoney {
    private static final int MIN_MONEY = 1_000;
    private static final int MAX_MONEY = 100_000_000;

    private final double money;

    private ParticipantMoney(final double money) {
        this.money = money;
    }

    public static ParticipantMoney create(final String money) {
        int validMoney = validType(money);
        validateRange(validMoney);
        return new ParticipantMoney(validMoney);
    }

    public static ParticipantMoney create(final double money) {
        return new ParticipantMoney(money);
    }

    public static ParticipantMoney zero() {
        return new ParticipantMoney(0);
    }

    private static int validType(final String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("베팅 금액은 정수 값이어야 합니다.");
        }
    }

    private static void validateRange(final int money) {
        if (money < MIN_MONEY || money > MAX_MONEY) {
            throw new IllegalArgumentException(String.format("베팅 금액은 %d원 이상, %d원 이하여야 합니다.",
                    MIN_MONEY, MAX_MONEY));
        }
    }

    public ParticipantMoney add(final ParticipantMoney participantMoney) {
        return new ParticipantMoney(this.money + participantMoney.money);
    }

    public ParticipantMoney subtract(final ParticipantMoney participantMoney) {
        return new ParticipantMoney(this.money - participantMoney.money);
    }

    public ParticipantMoney multiply(final double multiplier) {
        return new ParticipantMoney(this.money * multiplier);
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) return true;
        if (target == null || getClass() != target.getClass()) return false;
        final ParticipantMoney that = (ParticipantMoney) target;
        return money == that.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    public double getMoney() {
        return money;
    }
}
