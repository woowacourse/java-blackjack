package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class NamesTest {

    @DisplayName("입력한 플레이어의 수가 1명 이상 20명 이하가 아니라면 예외처리한다.")
    @Test
    void validate_number_of_players() {
        // given
        String names = "one,two,three,four,five,six,seven,eight,nine,ten,eleven,twelve,thirteen,fourteen,fifteen,sixteen,seventeen,eighteen,nineteen,twenty,twentyOne";

        // when & then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Names(names))
                .withMessageContaining("플레이어 이름의 수가 1개 이상 20개 이하여야 합니다.");
    }

}