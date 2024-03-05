package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CardShapeTest {
    @DisplayName("순서로 카드 모양을 결정한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,SPADE", "2,DIAMOND", "3,HEART", "4,CLOVER"})
    void generate(int givenOrder, CardShape expected) {
        CardShape cardShape = CardShape.generate(givenOrder);

        assertThat(cardShape).isEqualTo(expected);
    }

    @DisplayName("유효하지 않은 숫자를 입력하면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 5, 6})
    void validate(int givenOrder) {
        assertThatThrownBy(() -> CardShape.generate(givenOrder))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
