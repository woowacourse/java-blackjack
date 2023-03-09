package view.display;

import domain.card.Value;
import java.util.EnumMap;
import java.util.Map;

public final class ValueDisplay {

    private final Map<Value, String> valueDisplay;

    public ValueDisplay() {
        this.valueDisplay = initializeValueDisplay();
    }

    public String findDisplayOf(Value value) {
        return valueDisplay.get(value);
    }

    private Map<Value, String> initializeValueDisplay() {
        Map<Value, String> valueDisplay = new EnumMap<>(Value.class);
        valueDisplay.put(Value.ACE, "A");
        valueDisplay.put(Value.TWO, "2");
        valueDisplay.put(Value.THREE, "3");
        valueDisplay.put(Value.FOUR, "4");
        valueDisplay.put(Value.FIVE, "5");
        valueDisplay.put(Value.SIX, "6");
        valueDisplay.put(Value.SEVEN, "7");
        valueDisplay.put(Value.EIGHT, "8");
        valueDisplay.put(Value.NINE, "9");
        valueDisplay.put(Value.TEN, "10");
        valueDisplay.put(Value.JACK, "J");
        valueDisplay.put(Value.QUEEN, "Q");
        valueDisplay.put(Value.KING, "K");
        return valueDisplay;
    }
}
