package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("아무 이름도 들어오지 않는 경우 에러 테스트")
    @Test
    void nothing() {
        Map<String, Long> playersInfo = new LinkedHashMap<>();
        assertThatThrownBy(() -> new Players(playersInfo)).isInstanceOf(IllegalArgumentException.class);
    }
}
