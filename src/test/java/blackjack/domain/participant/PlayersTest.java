package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("플레이어들 객체 생성자 테스트")
    @Test
    void create() {
        Players players = new Players(List.of("pobi"));

        assertThat(players).isNotNull();
    }

}
