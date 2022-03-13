package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void createByDuplicatedName() {
        assertThatThrownBy(() -> Players.of(List.of("안녕", "hello", "안녕")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 플레이어는 존재할 수 없습니다.");
    }
}