import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
