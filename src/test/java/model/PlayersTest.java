package model;

import static model.Players.NAMES_AND_BETTING_NOT_MATCH_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void createByDuplicatedName() {
        assertThatThrownBy(() -> Players.of(List.of("안녕", "hello", "안녕"), List.of(1L,2L,3L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 플레이어는 존재할 수 없습니다.");
    }

    @Test
    void createInvalidSizeNameAndBetting() {
        assertThatThrownBy(() -> Players.of(List.of("안녕", "hello", "안녕"), List.of(1L,2L,3L,4L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NAMES_AND_BETTING_NOT_MATCH_MESSAGE);
    }
}