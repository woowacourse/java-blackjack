package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("플레이어 이름 목록이 비어있으면 예외가 발생한다.")
    void fromPlayerNicknames_ThrowsException_WhenEmpty() {
        assertThatThrownBy(() -> Players.fromPlayerNicknames(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 명 이상의 플레이어 닉네임을 입력해주세요");
    }

    @Test
    @DisplayName("플레이어 이름 목록에 중복이 존재하면 예외가 발생한다.")
    void fromPlayerNicknames_ThrowsException_WhenDuplicate() {
        assertThatThrownBy(() -> Players.fromPlayerNicknames(List.of("pobi", "pobi")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("정상적인 이름 목록이 주어지면 Players 객체가 생성된다.")
    void fromPlayerNicknames_CreatesPlayers() {
        Players players = Players.fromPlayerNicknames(List.of("pobi", "jason"));

        assertThat(players.getPlayers()).hasSize(2);
    }
}
