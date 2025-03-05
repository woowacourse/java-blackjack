package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("플레이어 이름 중 중복이 존재하면 예외가 발생한다.")
    void 플레이어_이름_중복_존재_예외() {
        //given
        List<String> names = List.of("pobi", "daro", "pobi");

        assertThatThrownBy(() -> Players.from(names))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
