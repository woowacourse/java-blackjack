package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("참여자 이름 중복시 예외가 발생한다.")
    void duplicatePlayerName() {
        //given
        List<String> names = List.of("redy", "redy");

        //when & then
        Assertions.assertThatThrownBy(() -> Players.from(names))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
