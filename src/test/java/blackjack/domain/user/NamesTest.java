package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class NamesTest {

    @DisplayName("입력한 플레이어의 수가 1명 이상 20명 이하가 아니라면 예외처리한다.")
    @Test
    void validate_number_of_players() {
        // given
        String names = "one,two,three,four,five,six,seven,eight,nine,ten,eleven,twelve,thirteen,fourteen,fifteen,sixteen,seventeen,eighteen,nineteen,twenty,twentyOne";
        List<String> splitNames = Arrays.stream(names.split(","))
                .collect(Collectors.toList());

        // when & then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Names(splitNames))
                .withMessageContaining("플레이어 이름의 수가 1개 이상 20개 이하여야 합니다.");
    }

}