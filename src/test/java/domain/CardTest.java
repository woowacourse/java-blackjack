package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class CardTest {

    @ParameterizedTest
    @MethodSource("royalStrings")
    @DisplayName("J,Q,K면 카드의 가치가 10어야한다.")
    void royal_test(String cardStr) {
        Card card = new Card(CardRank.QUEEN, CardMark.SPADE);

        int expected = 10;
        int actual = card.score();

        assertEquals(expected, actual);
    }

    static Stream<String> royalStrings() {
        return Stream.of("J", "Q", "K");
    }
}