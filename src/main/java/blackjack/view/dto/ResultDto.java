package blackjack.view.dto;

public class ResultDto {

    public static final String DEALER_NAME = "딜러";

    private final String name;
    private final double amount;

    public ResultDto(final double amount) {
        this(DEALER_NAME, amount);
    }

    public ResultDto(final String name, final double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public double getAmount() {
        return this.amount;
    }
}
