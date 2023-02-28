package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CardTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13})
    @DisplayName("카드 정상 생성 테스트")
    void validCardTest(int value) {
        assertDoesNotThrow(() -> {
            new Card(Shape.DIAMOND, CardNumber.of(value));
        });
    }



}
