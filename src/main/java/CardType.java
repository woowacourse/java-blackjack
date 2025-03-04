import common.ErrorMessage;
import java.util.function.Predicate;

public enum CardType {

    HEART(value -> 2 <= value && value <= 10),
    DIAMOND(value -> 2 <= value && value <= 10),
    CLOVER(value -> 2 <= value && value <= 10),
    SPADE(value -> 2 <= value && value <= 10),
    ACE(value -> value == 1 || value == 11),
    KING(value -> value == 10),
    QUEEN(value -> value == 10),
    JACK(value -> value == 10);

    private final Predicate<Integer> valueCondition;

    CardType(Predicate<Integer> valueCondition) {
        this.valueCondition = valueCondition;
    }

    public void validate(int value) {
        if (!this.valueCondition.test(value)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CARD_VALUE.getMessage());
        }
    }

    public boolean isValidate(int value) {
        return this.valueCondition.test(value);
    }
}
