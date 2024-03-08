package blackjack.domain.player;

import fixture.PlayerFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("참가자들 테스트")
class PlayersTest {

    @DisplayName("참가자들 중 이름이 중복되는 경우는 생성 검증에 실패한다")
    @Test
    void testCreatePlayersWithDuplicateNames() {
        Player player1 = PlayerFixture.of("리비", 1, 2);
        Player player2 = PlayerFixture.of("리비", 3, 4);

        assertThatThrownBy(() -> new Players(List.of(player1, player2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 중복되는 플레이어의 이름이 존재합니다");
    }

    @DisplayName("플레이어가 없으면 생성에 실패한다")
    @Test
    void testCreatePlayersWithEmptyEntry() {
        assertThatThrownBy(() -> new Players(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어가 없습니다");
    }

    @DisplayName("딜러를 제외한 게임 참여자가 3명을 넘는 경우는 생성 검증에 실패한다")
    @Test
    void testCreatePlayersWithInvalidEntryCount() {
        Player player1 = PlayerFixture.of("리비", 1, 2);
        Player player2 = PlayerFixture.of("제리", 3, 4);
        Player player3 = PlayerFixture.of("잉크", 1, 2);
        Player player4 = PlayerFixture.of("트레", 3, 4);

        assertThatThrownBy(() -> new Players(List.of(player1, player2, player3, player4)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 수는 3이하여야 합니다");
    }

    @DisplayName("생성 검증을 모두 통과하면 생성에 성공한다")
    @Test
    void testCreateWithValidPlayers() {
        Player player1 = PlayerFixture.of("리비", 1, 2);
        Player player2 = PlayerFixture.of("제리", 3, 4);

        assertThatCode(() -> new Players(List.of(player1, player2)))
                .doesNotThrowAnyException();
    }
}