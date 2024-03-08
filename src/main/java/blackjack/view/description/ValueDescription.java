package blackjack.view.description;

import blackjack.domain.card.Value;
import java.util.Arrays;

public enum ValueDescription {
    ACE(Value.ACE, "A"),
    TWO(Value.TWO, "2"),
    THREE(Value.THREE, "3"),
    FOUR(Value.FOUR, "4"),
    FIVE(Value.FIVE, "5"),
    SIX(Value.SIX, "6"),
    SEVEN(Value.SEVEN, "7"),
    EIGHT(Value.EIGHT, "8"),
    NINE(Value.NINE, "9"),
    TEN(Value.TEN, "10"),
    JACK(Value.JACK, "J"),
    QUEEN(Value.QUEEN, "Q"),
    KING(Value.KING, "K");

    final Value value;
    final String description;

    ValueDescription(Value value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static String getDescription(Value value) {
        return Arrays.stream(values())
                .filter(valueDescription -> valueDescription.value == value)
                .findFirst()
                .map(ValueDescription::getDescription)
                .orElseThrow();
    }
}
