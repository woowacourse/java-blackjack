package blackjack.domain.vo;

import static blackjack.TestUtils.createPlayerByName;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameTest {

    @DisplayName("플레이어 생성시 이름 검증")
    @Test
    public void testBlankName() {
        //given
        String name = " ";

        //when & then
        assertThatThrownBy(() -> createPlayerByName(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어 생성시 이름 검증")
    @Test
    public void testZeroName() {
        //given
        String name = "";

        //when & then
        assertThatThrownBy(() -> createPlayerByName(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
