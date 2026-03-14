package domain.card;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {

    private static final Map<String, Card> CACHE = new HashMap<>();

    static {
        Arrays.stream(CardPattern.values())
                .forEach(pattern -> Arrays.stream(CardNumber.values())
                        .forEach(number -> {
                            String key = generateKey(number.getCourt(), pattern.getName());
                            CACHE.put(key, new Card(number.getCourt(), pattern.getName()));
                        }));
    }

    private final CardPattern pattern;
    private final CardNumber number;

    public Card(String number, String pattern) {
        this.number = CardNumber.matchCardNumber(number);
        this.pattern = CardPattern.matchCardPattern(pattern);
    }

    public static Card from(String number, String pattern) {
        String key = generateKey(number, pattern);
        if (!CACHE.containsKey(key)) {
            throw new IllegalArgumentException("존재하지 않는 카드 조합입니다: " + key);
        }
        return CACHE.get(key);
    }

    private static String generateKey(String number, String pattern) {
        return number + pattern;
    }

    public int number() {
        return number.getValue();
    }

    public String cardName() {
        return number.getCourt() + pattern.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Card card)) {
            return false;
        }
        return pattern == card.pattern && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, number);
    }
}
