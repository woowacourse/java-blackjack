package util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class ParserTest {
    @Test
    @DisplayName("플레이어의 이름을 공백 제거 후, 쉼표(,)로 구분한다.")
    void 플레이어_이름_파싱_테스트() {
        // given
        Parser parser = new Parser();
        String playersName = "라이,    영기";

        // when
        List<String> parsedPlayersName = parser.parsePlayersName(playersName);

        // then
        assertThat(parsedPlayersName).containsExactly("라이", "영기");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("베팅 금액이 비어 있는 경우, IllegalArgumentException이 발생한다.")
    void 베팅_금액_빈_입력_테스트(String bettingMoney) {
        // given
        Parser parser = new Parser();

        // when & then
        assertThatThrownBy(() -> parser.parseBettingMoney(bettingMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 숫자 외의 문자를 포함할 수 없습니다.");
    }

    @Test
    @DisplayName("베팅 금액에 숫자 외의 문자가 포함되는 경우, IllegalArgumentException이 발생한다.")
    void 배팅_금액_숫자_외의_문자_예외_테스트() {
        // given
        Parser parser = new Parser();
        String bettingMoney = "1000원";

        // when & then
        assertThatThrownBy(() -> parser.parseBettingMoney(bettingMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 숫자 외의 문자를 포함할 수 없습니다.");
    }
}
