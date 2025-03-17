package Blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import Blackjack.domain.game.Participants;
import Blackjack.exception.ErrorException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("이름이 중복되는 참여자 예외 테스트")
    void duplicateParticipantNamesTest() {
        // given
        String name = "pobi,jason,pobi";
        List<String> names = Arrays.stream(name.split(",")).toList();
        // when & then
        assertThatThrownBy(() -> new Participants(names))
                .isInstanceOf(ErrorException.class)
                .hasMessageContaining("[ERROR] 참여자 이름은 중복될 수 없습니다.");
    }
}
