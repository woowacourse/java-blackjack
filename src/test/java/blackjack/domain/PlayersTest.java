package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class PlayersTest {
    @Test
    @DisplayName("플레이어는 여러명일 수 있다.")
    void playersCreateTest() {
        // given
        List<String> names = List.of("pobi", "lemone", "seyang");
        List<String> expectedNames = List.of("pobi", "lemone", "seyang");

        // when
        Players players = Players.from(names);

        // then
        assertThat(players.getNames())
                .usingRecursiveComparison()
                .isEqualTo(expectedNames);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "    "})
    @DisplayName("플레이어가 없을 경우 예외가 발생한다.")
    void validateNoPlayer(String names) {
        // given & when & then
        assertThatCode(() -> Players.from(List.of(names)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("공백이 아닌 플레이어를 입력해 주세요.");
    }

    @Test
    @DisplayName("플레이어 이름이 중복될 경우 예외가 발생한다.")
    void validateDuplicate() {
        // given
        List<String> names = List.of("pobi", "pobi");
        // when & then
        assertThatCode(() -> Players.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어 반복 테스트를 한다.")
    void playersForEachTest() {
        // given
        Players players = Players.from(List.of("seyang", "lemone"));
        AtomicInteger count = new AtomicInteger();

        // when
        players.forEach(player -> count.getAndIncrement());

        // then
        assertThat(count.get()).isEqualTo(2);
    }
}
