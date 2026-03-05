package domain;

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

    public static int getScore(int card) {
        int cardNumber = card % 13;

        if (1 <= cardNumber && cardNumber <= 9) {
            return cardNumber + 1;
        }

        if (cardNumber >= 10) {
            return 10;
        }

        return 11;
    }

    public static String getCardName(int score) {
        int shape = score / 13;
        int number = score % 13;
        return RANK_MAP.get(number) + SUIT_MAP.get(shape);
    }
}
