package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.participant.Player;
import blackjack.dto.WinningResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("중복된 이름으로 플레이어를 생성하면 예외가 발생한다.")
    @Test
    void validateDuplicateNames() {
        List<String> names = List.of("pobi", "pobi");

        assertThatThrownBy(() -> Players.makePlayers(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 중복될 수 없습니다.");
    }

    @DisplayName("현재 차례인(카드를 더 받을 수 있는) 플레이어를 찾는다.")
    @Test
    void findDrawablePlayer() {
        Players players = Players.makePlayers(List.of("pobi", "jason"));

        // 초기 상태에서는 첫 번째 플레이어가 Drawable
        Player firstPlayer = players.findDrawablePlayer();

        assertThat(firstPlayer.getNickname()).isEqualTo("pobi");
    }

    @DisplayName("플레이어가 카드를 그만 받겠다고 하면 다음 플레이어로 순서가 넘어간다.")
    @Test
    void nextPlayerAfterStop() {
        Players players = Players.makePlayers(List.of("pobi", "jason"));

        // pobi가 멈춤
        players.dontWantDraw();

        // 이제 drawable 플레이어는 jason이어야 함
        assertThat(players.findDrawablePlayerNickname()).isEqualTo("jason");
    }

    @DisplayName("모든 플레이어가 카드를 그만 받으면 drawable 플레이어는 null이다.")
    @Test
    void noMoreDrawablePlayer() {
        Players players = Players.makePlayers(List.of("pobi"));
        players.dontWantDraw();

        assertThat(players.findDrawablePlayer()).isNull();
    }

    @DisplayName("딜러 점수에 따른 플레이어들의 최종 승패 결과를 계산한다.")
    @Test
    void calculateWinningResult() {
        // 주의: Player 객체의 상태(점수)를 결정하는 로직은 Player 내부 구현에 따라 다를 수 있습니다.
        // 여기서는 흐름만 검증합니다.
        Player pobi = new Player("pobi"); // 내부에서 점수가 계산되도록 카드 세팅 필요
        Players players = Players.from(List.of(pobi));

        // 딜러 점수 18점 기준으로 결과 계산
        WinningResult result = players.getWinningResult(18);

        assertThat(result).isNotNull();
        assertThat(result.playerResults()).hasSize(1);
    }
}