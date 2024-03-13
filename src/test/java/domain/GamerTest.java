package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamerTest {
    @DisplayName("Gamer는 본인의 이름 정보를 제공한다.")
    @Test
    void getName() {
        // given
        Gamer gamer = new Gamer("pobi");

        // when
        String actual = gamer.getName();

        // then
        String expected = "pobi";
        assertThat(actual).isEqualTo(expected);
    }
}
