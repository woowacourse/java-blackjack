package blackjack.view.dto;

public class ResultDto {

    public static final String DEALER_NAME = "딜러";

    private final String name;
    private final String result;

    public ResultDto(final String result) {
        this(DEALER_NAME, result);
    }

    public ResultDto(final String name, final String result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return this.name;
    }

    public String getResult() {
        return this.result;
    }
}
