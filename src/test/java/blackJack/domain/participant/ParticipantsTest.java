package blackJack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Test
    void 플레이어의_이름이_서로_중복되는_경우_예외가_발생한다() {
        List<String> playerNames = List.of("rookie", "rookie");

        assertThatThrownBy(() -> new Participants(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("딜러를 포함한 참가자들의 이름은 중복될 수 없습니다.");
    }

    @Test
    void 플레이어의_이름이_딜러와_중복되는_경우_예외가_발생한다() {
        List<String> playerNames = List.of("rookie", "딜러");

        assertThatThrownBy(() -> new Participants(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("딜러를 포함한 참가자들의 이름은 중복될 수 없습니다.");
    }

    @Test
    void 플레이어의_수가_1명에서_7명_사이가_아니라면_예외가_발생한다() {
        List<String> playerNames = List.of("k1", "k2", "k3", "k4", "k5", "k6", "k7", "k8");

        assertThatThrownBy(() -> new Participants(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 인원수는 1명 이상 7명 이하여야 합니다.");
    }
}
