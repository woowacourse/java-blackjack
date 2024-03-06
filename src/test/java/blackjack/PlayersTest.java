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
        var player1 = PlayerFixture.게임_플레이어_생성(new Name("초롱"));
        var player2 = PlayerFixture.게임_플레이어_생성(new Name("조이썬"));
        var dealer = PlayerFixture.딜러_생성(new Name("딜러"));

        assertThatCode(() -> {
            new Players(List.of(player1, player2, dealer));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어 목록에는 한 명의 딜러만 가능하다.")
    public void Players_Instance_contain_one_dealer() {
        var player = PlayerFixture.게임_플레이어_생성(new Name("초롱"));
        var dealer1 = PlayerFixture.딜러_생성(new Name("딜러"));
        var dealer2 = PlayerFixture.딜러_생성(new Name("보조딜러"));

        assertThrows(IllegalStateException.class, () -> {
            new Players(List.of(player, dealer1, dealer2));
        });
    }
}
