package blackjack.view;

public enum ResultSymbol {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String symbol;

    ResultSymbol(String symbol) {
        this.symbol = symbol;
    }

    public static String getMappingSymbol(String result) {
        return ResultSymbol.valueOf(result).symbol;
    }

    public static ResultSymbol getOpposite(String result) {
        if (result.equals(WIN.name())) {
            return LOSE;
        }
        if (result.equals(LOSE.name())) {
            return WIN;
        }
        return DRAW;
    }

    public String getSymbol() {
        return symbol;
    }
}
