package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("숫자와 모양이 같다면 같은 객체이다")
    void test1() {
        // given
        Card card1 = new Card(1, CardType.SPACE);
        Card card2 = new Card(1, CardType.SPACE);

        //when & then
        assertThat(card1).isEqualTo(card2);
    }
}
