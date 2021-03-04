package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CardNumber {

    private static final Map<String, CardNumber> NUMBER_CACHE = new HashMap<>();

    private final String cardNumber;
    private final int value;

    static {
        for (NumberCandidate numberCandidate : NumberCandidate.values()) {
            CardNumber cardNumber = new CardNumber(numberCandidate.getNumber(),
                numberCandidate.getValue());
            NUMBER_CACHE.put(numberCandidate.getNumber(), cardNumber);
        }
    }

    private CardNumber(String number, int value) {
        this.cardNumber = number;
        this.value = value;
    }

    public static CardNumber from(String number) {
        return NUMBER_CACHE.get(number);
    }

    public int getValue() {
        return this.value;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardNumber that = (CardNumber) o;
        return value == that.value && Objects.equals(cardNumber, that.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, value);
    }
}
