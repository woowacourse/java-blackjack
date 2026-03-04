package domain;

import org.junit.jupiter.api.Test;
import util.Parser;
import util.StringParser;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {

    @Test
    void 플레이어_셍성_테스트() {
        // given
        String playerNamesInput = "phobi,jason";
        Parser parser = new StringParser();
        List<String> playerNames = parser.splitToDelimiter(playerNamesInput, ",");

        // when
        // 플레이어 리스트 = 플레이어 객체를 만드는 메소드 실행
        List<Player> players = playerNames.stream()
                .map(Player::of)
                .toList();

        // then
        assertThat(players).isNotNull();
        assertThat(players.size()).isEqualTo(2);
        assertThat(players.get(0).getName()).isEqualTo("phobi");
        assertThat(players.get(1).getName()).isEqualTo("jason");
    }
}
