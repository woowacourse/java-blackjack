package model;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("총 플레이어 수가 1명 이상이면 객체 생성 성공")
    @Test
    void testValidSizeOfPlayers() {
        List<String> playerNames = List.of("lily", "jojo");
        assertThatCode(() -> Players.from(playerNames)).doesNotThrowAnyException();
    }
}
