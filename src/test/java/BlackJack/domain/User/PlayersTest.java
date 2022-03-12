package BlackJack.domain.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    @Test
    @DisplayName("유저가 이름을 입력한만큼 참가자들 생성되는지 테스트")
    void joinTest() {
        List<String> input = List.of("기론", "열음");
        Players players = Players.join(input);
        assertThat(players.size()).isEqualTo(input.size());
    }
}