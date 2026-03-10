package blackjack.util;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.user.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}