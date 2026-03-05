package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("이름 공백 제거 후 성공")
    @Test
    void 이름이_정상적으로_들어왔을때() {
        String names = "아나키, 포비, 모아";
        List<String> playerName = Arrays.stream(names.split(","))
                .map(String::trim)
                .toList();

        Players players = new Players(playerName);

        assertThat(players.getSize()).isEqualTo(3);

    }
}
