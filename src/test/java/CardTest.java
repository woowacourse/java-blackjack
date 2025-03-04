import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardTest {

    @DisplayName("카드의 숫자를 반환한다")
    @Test
    void test() {
        //given
        Card card1 = new Card("2", "spade");
        //when
        int number = card1.getNumber();
        //then
        assertThat(number).isEqualTo(2);
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
}
