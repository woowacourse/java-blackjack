package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.BattingAmount;
import blackjack.fixture.PlayerFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    @DisplayName("플레이어 목록을 포함한 일급 컬렉션을 생성한다.")
    public void Players_Instance_create_with_playerList() {
        var player1 = PlayerFixture.게임_플레이어_생성(new Name("초롱"), new BattingAmount("10000"));
        var player2 = PlayerFixture.게임_플레이어_생성(new Name("조이썬"), new BattingAmount("10000"));
        var dealer = PlayerFixture.딜러_생성(new Name("딜러"));

        assertThatCode(() -> {
            new Players(dealer, List.of(player1, player2));
        }).doesNotThrowAnyException();
    }
}
