package card;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("CardValue 은")
class CardValueTest {

    @ParameterizedTest
    @CsvSource(value = {
            "TWO -> 2",
            "THREE -> 3",
            "FOUR -> 4",
            "FIVE -> 5",
            "SIX -> 6",
            "SEVEN -> 7",
            "EIGHT -> 8",
            "NINE -> 9",
            "TEN -> 10",
            "KING -> 10",
            "QUEEN -> 10",
            "JACK -> 10",
            "ACE -> 1",
    }, delimiterString = " -> ")
    void 값을_가진다(final CardValue cardValue, final int value) {
        // when & then
        assertEquals(cardValue.value(), value);
    }
}