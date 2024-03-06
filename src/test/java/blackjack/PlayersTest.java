package blackjack;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.fixture.PlayerFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("플레이어 목록을 포함한 일급 컬렉션을 생성한다.")
    public void Players_Instance_create_with_playerList() {
        var player1 = PlayerFixture.게임_플레이어_생성(List.of(CardValue.TWO, CardValue.THREE));
        var player2 = PlayerFixture.게임_플레이어_생성(List.of(CardValue.TWO, CardValue.THREE));

        assertThatCode(() -> {
            new Players(List.of(player1, player2));
        }).doesNotThrowAnyException();
    }
}
