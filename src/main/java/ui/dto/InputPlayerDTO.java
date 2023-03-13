package ui.dto;

public class InputPlayerDTO {
    private final String name;
    private final int bettingAmount;

    public InputPlayerDTO(String name, int bettingAmount) {
        this.name = name;
        this.bettingAmount = bettingAmount;
    }

    public String getName() {
        return name;
    }

    public int getBettingAmount() {
        return bettingAmount;
    }
}
