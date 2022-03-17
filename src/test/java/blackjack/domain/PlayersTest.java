package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("아무 이름도 들어오지 않는 경우 에러 테스트")
    @Test
    void nothing() {
        List<String> playerNames = new ArrayList<>();

        assertThatThrownBy(() -> new Players(playerNames)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름 중복 에러 테스트")
    @Test
    void duplicate() {
        List<String> playerNames = List.of("pobi", "pobi");

        assertThatThrownBy(() -> new Players(playerNames)).isInstanceOf(IllegalArgumentException.class);
    }
}
