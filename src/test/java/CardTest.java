import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CardTest {

    @DisplayName("카드의 숫자를 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"2", "3", "4", "5", "6", "7", "8", "9", "10"})
    void test(String value) {
        //given
        Card card1 = new Card(value, "spade");
        //when
        int number = card1.getNumber();
        //then
        assertThat(number).isEqualTo(Integer.parseInt(value));
    }

    @DisplayName("Q, K, J는 10을 반환한다")
    @ParameterizedTest
    @CsvSource({"Q", "K", "J"})
    void 문자로_설정된_카드의_숫자를_반환한다(String value) {
        //given
        Card card1 = new Card(value, "spade");
        //when
        final int number = card1.getNumber();
        //then
        assertThat(number).isEqualTo(10);
    }

    @DisplayName("2 ~ 10 범위에 해당하지 않는 값의 카드는 생성할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "11", "12", "100"})
    void 생성자_숫자_검증_테스트(String value) {
        // when & then
        assertThatThrownBy(() -> {
            new Card(value, "spade");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
