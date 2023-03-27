package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantsTest {

    @Test
    @DisplayName("이름들을 받아 사용자 리스트 players를 생성한다")
    void createPlayersTest() {
        List<String> names = List.of("jamie", "boxster");

        Participants participants = Participants.from(names);

        assertThat(participants.getPlayers()).hasSize(2);
    }

    @Test
    @DisplayName("중복인 이름이 있는 경우 예외를 반환한다")
    void duplicateNamesTest() {
        List<String> names = List.of("jamie", "jamie");

        assertThatThrownBy(() -> Participants.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복인 이름은 입력하실 수 없습니다.");
    }

    @Test
    @DisplayName("공백을 포함된 이름은 공백 제거 후 중복 체크를 통해 예외를 반환한다")
    void duplicateNamesWithBlankTest() {
        List<String> names = List.of("jamie", " jamie ");

        assertThatThrownBy(() -> Participants.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복인 이름은 입력하실 수 없습니다.");
    }
}
