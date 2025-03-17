package domain;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerNamesTest {

    @Test
    @DisplayName("플레이어가 6명 이상이면 예외를 반환합니다.")
    void validatePlayerQuantityTest() {
        List<PlayerName> usernames = Stream.of("김", "이", "박", "정", "최", "유").map(PlayerName::new).toList();

        Assertions.assertThatThrownBy(() -> new PlayerNames(usernames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 최대 5명까지 참가 가능합니다.");
    }

    @Test
    @DisplayName("중복된 플레이어가 있으면 예외를 반환합니다.")
    void validatePlayerDuplicateTest() {
        List<PlayerName> usernames = Stream.of("김", "이", "박", "정", "최", "김").map(PlayerName::new).toList();

        Assertions.assertThatThrownBy(() -> new PlayerNames(usernames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 중복일 수 없습니다.");
    }
}
