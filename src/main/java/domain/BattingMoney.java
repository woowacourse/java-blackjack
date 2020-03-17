package domain;

public class BattingMoney {
    private final int battingMoney;

    public BattingMoney(int battingMoney) {
        if (battingMoney < 0) {
            throw new NegativeMoneyException("Money는 음수일 수 없습니다.");
        }
        this.battingMoney = battingMoney;
    }

    public BattingMoney(String battingMoney) {
        try {
            this.battingMoney = Integer.parseInt(battingMoney);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바른 값을 입력해주세요.");
        }
    }
}
