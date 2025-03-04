import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardTest {

    @DisplayName("카드의 숫자를 반환한다")
    @ParameterizedTest
    @CsvSource({
            "2, spade"
    })
    void test(String value, String type) {
        //given
        Card card1 = new Card(value, type);
        //when
        int number = card1.getNumber();
        //then
        assertThat(number).isEqualTo(2);
    }
}
