package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardTest {

    @Test
    @DisplayName("유효한 카드 숫자라면 정상적으로 생성된다.")
    void card_Number_Valid() {
        Card card = new Card(1, Shape.CLUB);
        assertThat(card).extracting("cardNumber").isEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("유효한 카드 숫자가 아니라면 예외가 발생한다.")
    @ValueSource(ints = {-1, 14})
    void card_Number_Invalid(int number) {
        assertThatThrownBy(() -> new Card(number, Shape.CLUB))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("유효한 모양이라면 정상적으로 생성된다.")
    void shape() {
        Card card = new Card(1, Shape.DIAMONDS);

        assertThat(card).extracting("shape").isEqualTo(Shape.DIAMONDS);
    }
}
