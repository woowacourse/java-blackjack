package domain;

public class Bet {

    private final int amount;

    public Bet(String amount) {
        this.amount = parseAmount(amount);
    }

    private int parseAmount(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 숫자만 입력 가능합니다.");
        }
    }

}
