package domain.card;

import java.util.Map;

public final class CardSuitMap {
    private static final Map<Integer, String> SUIT_MAP = Map.of(
            0, "스페이드",
            1, "하트",
            2, "다이아몬드",
            3, "클로버"
    );

    private static final Map<Integer, String> RANK_MAP = Map.ofEntries(
            Map.entry(0, "A"),
            Map.entry(1, "2"),
            Map.entry(2, "3"),
            Map.entry(3, "4"),
            Map.entry(4, "5"),
            Map.entry(5, "6"),
            Map.entry(6, "7"),
            Map.entry(7, "8"),
            Map.entry(8, "9"),
            Map.entry(9, "10"),
            Map.entry(10, "J"),
            Map.entry(11, "Q"),
            Map.entry(12, "K")
    );

    private CardSuitMap() {

    }

    public static String getSuit(int shape) {
        return SUIT_MAP.get(shape);
    }

    public static String getRank(int number) {
        return RANK_MAP.get(number);
    }
}

