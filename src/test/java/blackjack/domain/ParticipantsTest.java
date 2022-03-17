package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.player.Participants;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Test
    @DisplayName("참가자의 이름이 null일 경우 예외를 발생시킨다.")
    void throwExceptionWhenNull() {
        List<String> names = null;
        assertThatThrownBy(() -> new Participants(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 한 명 이상의 참가자를 입력해주세요.");
    }

    @Test
    @DisplayName("참가자의 이름이 중복될 경우 예외를 발생시킨다.")
    void throwExceptionWhenDuplicate() {
        List<String> names = List.of("엘리", "배카라", "배카라");
        assertThatThrownBy(() -> new Participants(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자의 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("참가자가 8명 초과인 경우 예외를 발생시킨다.")
    void throwExceptionWhenPlayerNumberBiggerThan8() {
        List<String> names = List.of("엘리", "배카라", "이브", "알렉스", "알린", "네오", "바니", "썬", "아스피");
        assertThatThrownBy(() -> new Participants(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자는 1명 이상, 8명 이하여야합니다.");
    }
}
