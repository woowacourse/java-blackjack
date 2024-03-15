package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class ParticipantsTest {
    @Test
    void 플레이어가_없을_경우_예외가_발생한다() {
        List<String> playerNames = Collections.emptyList();

        assertThatThrownBy(() -> Participants.createPlayers(playerNames))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어의 수는 1 ~ 6명이어야 합니다.");
    }

    @Test
    void 플레이어의_수가_최댓값을_넘을_경우_예외가_발생한다() {
        List<String> playerNames = List.of("뽀로로", "프린", "포비", "네오", "토미", "영이", "수달");

        assertThatThrownBy(() -> Participants.createPlayers(playerNames))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어의 수는 1 ~ 6명이어야 합니다.");
    }

    @Test
    void 플레이어의_이름이_중복될_경우_예외가_발생한다() {
        String roro = "뽀로로";
        String prin = "프린";
        List<String> playerNames = List.of(roro, prin, prin);

        assertThatThrownBy(() -> Participants.createPlayers(playerNames))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 이름이 존재합니다.");
    }

    @Test
    void 플레이어들을_정상적으로_생성한다() {
        List<String> playerNames = List.of("뽀로로", "프린", "포비", "네오", "토미", "영이");
        Participants participants = Participants.createPlayers(playerNames);

        List<Player> players = participants.getPlayers();

        assertThat(players).hasSize(6);
    }
}
