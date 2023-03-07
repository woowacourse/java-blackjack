package blackjackgame.dto;

public class CardDto {
    private final String symbol;
    private final String value;

    public CardDto(final String symbol, final String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }
}
