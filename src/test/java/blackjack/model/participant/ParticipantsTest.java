package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @DisplayName("참가자가 8명 이상이면 예외를 발생한다.")
    @Test
    void construct_player_count() {
        List<String> names = List.of("리버", "포키", "크리스", "알렉스", "해리", "유니", "gamer", "duly", "ee");

        assertThatThrownBy(() -> new Participants(names, (name) -> 3000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자는 최대 8명 까지 참여 가능합니다.");
    }

    @DisplayName("참가자 이름중 중복되는 이름이 있으면 예외를 발생한다.")
    @Test
    void construct_duplicate_name() {
        List<String> names = List.of("리버", "포키", "크리스", "리버");

        assertThatThrownBy(() -> new Participants(names, (name) -> 3000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자 이름 중 중복되는 이름이 있습니다.");
    }
}