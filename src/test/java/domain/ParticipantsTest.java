package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dto.ParticipantsInitDTO;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.Money;

public class ParticipantsTest {
    @Test
    @DisplayName("참가자 수가 16명인 경우, 정상 동작한다.")
    void 참가자_수_정상_범위_테스트() {
        // given
        List<User> users = createUsers(16);

        // when & then
        assertThatCode(() -> new Participants(users))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("참가자 수가 17명인 경우, IllegalArgumentException이 발생한다.")
    void 참가자_수_초과_예외_테스트() {
        // given
        List<User> users = createUsers(17);

        // when & then
        assertThatThrownBy(() -> new Participants(users))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 최대 참가 인원은 16명 이하여야 합니다.");
    }

    private List<User> createUsers(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> new User("User" + i, new Money(1000)))
                .toList();
    }
}
