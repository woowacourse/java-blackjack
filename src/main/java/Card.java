import java.util.Objects;

public class Card {
    private final String type;
    private final String symbol;

    public Card(String type, String symbol) {
        validateType(type);
        validateSymbol(symbol);
        this.type = type;
        this.symbol = symbol;
    }

    private void validateType(String type) {
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException("잘못된 값이 들어왔습니다.");
        }
        if (!"다이아몬드".equals(type) && !"스페이드".equals(type) && !"하트".equals(type) && !"클로버".equals(type)) {
            throw new IllegalArgumentException("잘못된 값이 들어왔습니다.");
        }
    }

    private void validateSymbol(String symbol) {
        if (Objects.isNull(symbol)) {
            throw new IllegalArgumentException("잘못된 값이 들어왔습니다.");
        }
        if (!("A".equals(symbol) || "2".equals(symbol) || "3".equals(symbol) || "4".equals(symbol)
                || "5".equals(symbol) || "6".equals(symbol) || "7".equals(symbol) || "8".equals(symbol)
                || "9".equals(symbol) || "10".equals(symbol) || "J".equals(symbol) || "Q".equals(symbol)
                || "K".equals(symbol))) {
            throw new IllegalArgumentException("잘못된 값이 들어왔습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(type, card.type) &&
                Objects.equals(symbol, card.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, symbol);
    }

    public int getValue() {
        if ("A".equals(symbol)) {
            return 1;
        }
        if ("J".equals(symbol) || "Q".equals(symbol) || "K".equals(symbol)) {
            return 10;
        }
        return Integer.parseInt(symbol);
    }
}
