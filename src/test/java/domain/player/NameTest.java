package domain.player;

import domain.player.info.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @ParameterizedTest
    @DisplayName("참가자의 이름은 공백이 될수 없다.")
    @ValueSource(strings = {"\n", " ", ""})
    void givenBlankName_thenFail(String name) {
        //then
        assertThatThrownBy(() -> Name.of(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자의 이름을 입력해 주세요");
    }

    @Test
    @DisplayName("참가자의 이름이 10자 초과하면 예외가 발생한다.")
    void givenTenOverLengthName_thenFail() {
        final String tenLengthName = "0123456789_";
        //then
        assertThatThrownBy(() -> Name.of(tenLengthName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("10자 이하의 이름만 입력해 주세요");
    }
}
