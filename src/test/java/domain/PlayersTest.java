package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("플레이어들 객체를 생성한다.")
    void createPlayersTest() {
        Assertions.assertDoesNotThrow(()->new Players("pobi,jason"));
    }
}
