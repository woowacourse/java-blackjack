package view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Name;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputParserTest {

    @Test
    @DisplayName("플레이어의 이름 입력값들을 문자열 리스트 형태로 변환한다.")
    public void 플레이어의_이름_변환_성공() {
        // given
        final String strPlayerNames = "pobi, jason";

        // when
        final List<Name> parsedResult = InputParser.parsePlayers(strPlayerNames);

        // then
        assertThat(parsedResult).hasSize(2).extracting(Name::getName).containsExactlyInAnyOrder("pobi", "jason");
    }

    @Test
    @DisplayName("플레이어의 이름 길이가 맞지 않을 때 예외가 발생한다.")
    public void 플레이어_이름_길이_예외() {
        // given
        final String longPlayerNames = "p, abcdefghijk";

        // when
        assertThatThrownBy(() -> InputParser.parsePlayers(longPlayerNames)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 2글자 이상 10글자 이하여야 합니다.");
    }
}