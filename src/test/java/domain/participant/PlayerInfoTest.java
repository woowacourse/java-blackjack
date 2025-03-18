package domain.participant;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerInfoTest {
    @Test
    @DisplayName("플레이어 기본 정보 테스트")
    void playerInfoTest() {
        assertDoesNotThrow(() -> new PlayerInfo(new Name("pobi"), new Money(10000)));
    }

}
