package domain.card;

import java.util.Arrays;
import java.util.List;

public enum CardNumber {

    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, KING, QUEEN, JACK;

    public static List<CardNumber> getAll() {
        return Arrays.asList(values());
    }
}
