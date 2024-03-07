package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//TODO : 테스트 전반적으로 메서드 이름에 경계값 제거
class PlayersTest {

    @DisplayName("최소 한 명 이상의 플레이어가 존재해야 한다.")
    @Test
    void validateTest_countOfPlayersIsZero_throwException() {
        assertThatThrownBy(() -> Players.from(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최소 한 명의 플레이어가 있어야 합니다.");
    }

    @DisplayName("N 명 이상의 플레이어를 가지면 예외가 발생한다.")
    @Test
    void validateTest_countOfPlayersIsOverFour_throwException() {
        List<String> manyNames = List.of("1", "2", "3", "4", "5");

        assertThatThrownBy(() -> Players.from(manyNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 4명의 플레이어만 참여 가능합니다.");
    }

    //TODO : 중복
}
