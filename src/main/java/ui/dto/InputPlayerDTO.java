package ui.dto;

public class InputPlayerDTO {
    private final String name;
    private final int bettingAmount;

    public InputPlayerDTO(String name, int bettingAmount) {
        this.name = name;
        if (bettingAmount < 0) {
            throw new IllegalArgumentException("베팅 금액은 음수가 될 수 없습니다.");
        }
        this.bettingAmount = bettingAmount;
    }

    public String getName() {
        return name;
    }

    public int getBettingAmount() {
        return bettingAmount;
    }
}
