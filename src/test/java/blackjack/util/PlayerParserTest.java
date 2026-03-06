package blackjack.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerParserTest {

    @Test
    @DisplayName("플레이어 이름 파싱 및 플레이어 객체 생성")
    void test_parse_success() {
        String input = "pobi,james";

        List<Player> players = PlayerParser.parse(input);

        assertThat(players.size()).isEqualTo(2);
        assertThat(players)
                .extracting("name")
                .contains("pobi", "james");
    }

    @Test
    @DisplayName("플레이어의 이름에 영어와 한글이 아닌 문자가 존재하는 경우 예외 발생")
    void test_parse_fail_when_invalid_name() {
        String invalidInput = "pobi,james*";

        assertThatThrownBy(() -> PlayerParser.parse(invalidInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "  ,  ,  "})
    @DisplayName("플레이어의 이름이 공백인 경우 예외 발생")
    void test_parse_fail_when_name_is_null(String emptyName) {
        assertThatThrownBy(() -> PlayerParser.parse(emptyName))
                .isInstanceOf(IllegalArgumentException.class);
    }
}