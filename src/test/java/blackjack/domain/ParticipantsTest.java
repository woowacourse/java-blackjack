package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.player.Participants;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Test
    @DisplayName("참가자의 이름이 중복될 경우 예외를 발생시킨다.")
    void throwExceptionWhenDuplicate() {
        List<String> names = List.of("엘리", "배카라", "배카라");
        assertThatThrownBy(() -> new Participants(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자의 이름은 중복될 수 없습니다.");
    }
}
