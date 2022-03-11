package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PlayersTest {

    @DisplayName("중복된 이름을 입력했을 때 예외 발생을 확인한다.")
    @Test
    void duplicated_name_error() {
        assertThatThrownBy(() -> new Players("pobi,pobi"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 중복될 수 없습니다.");
    }

    @DisplayName("플레이어 최대 인원을 넘게 입력했을 때 예외 발생을 확인한다.")
    @Test
    void maximum_player_error() {
        String input = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26";
        assertThatThrownBy(() -> new Players(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 최대 인원은 25명 입니다.");
    }
}
