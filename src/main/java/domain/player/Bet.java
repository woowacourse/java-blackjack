package domain.player;

public class Bet {
    private final int value;

    public Bet(int value) {
        this.value = value;
    }

    public static Bet from(int value) {
        validateValue(value);
        return new Bet(value);
    }

    public static Bet makeEmptyBet() {
        return new Bet(0);
    }

    private static void validateValue(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("베팅액은 0보다 커야 합니다.");
        }
        if (value % 1000 != 0) {
            throw new IllegalArgumentException("베팅액은 1,000원 단위로 입력해야 합니다.");
        }
    }

    public Bet multiplyDouble() {
        return new Bet(this.value * 2);
    }
}
