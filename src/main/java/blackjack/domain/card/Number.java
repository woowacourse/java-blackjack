package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;

public enum Number {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private static final Map<Integer, Number> numberByOrder = new HashMap<>();

    private final int score;
    private final String symbol;

    static {
        for (Number number : Number.values()) {
            numberByOrder.put(numberByOrder.size(), number);
        }
    }

    Number(int score, String symbol) {
        this.score = score;
        this.symbol = symbol;
    }

    public int getScore() {
        return score;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Number getByOrder(int order) {
        if (numberByOrder.containsKey(order)) {
            throw new NullPointerException("존재하지 않는 카드 숫자입니다.");
        }
        return numberByOrder.get(order);
    }
}
