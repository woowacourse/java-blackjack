package blackjack.domain.card;

import java.util.EnumMap;
import java.util.Objects;

public class CardNumber {

    private static final EnumMap<NumberCandidate, CardNumber> NUMBER_CACHE =
        new EnumMap<>(NumberCandidate.class);

    private String alphabet;
    private int value;

    static {
        for (NumberCandidate numberCandidate : NumberCandidate.values()) {
            CardNumber cardNumber = new CardNumber(numberCandidate.getNumber(),
                numberCandidate.getValue());
            NUMBER_CACHE.put(numberCandidate, cardNumber);
        }
    }

    private CardNumber(String alphabet, int value) {
        this.alphabet = alphabet;
        this.value = value;
    }

    public static CardNumber from(String number) {
        NumberCandidate numberCandidate = NumberCandidate.matchNumber(number);
        return NUMBER_CACHE.get(numberCandidate);
    }

    public int getValue() {
        return this.value;
    }

    public String getAlphabet() {
        return alphabet;
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
        return value == that.value && Objects.equals(alphabet, that.alphabet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alphabet, value);
    }
}
