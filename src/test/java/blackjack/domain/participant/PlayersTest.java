package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PlayersTest {
    @Test
    @DisplayName("Players가 정상 생성되는지 테스트")
    void init() {
        List<String> playerName = Arrays.asList("joel", "bada", "jon");
        assertThatCode(() -> Players.of(playerName))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Players에 중복된 이름이 입력되면 에러가 발생한다")
    void duplicateNameInit() {
        List<String> playerName = Arrays.asList("joel", "joel");
        assertThatThrownBy(() -> Players.of(playerName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 플레이어의 이름이 중복됩니다.");
    }
}
