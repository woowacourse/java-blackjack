package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class BlackjackGameTest {

    @Test
    @DisplayName("Map에 key가 없을 때 null을 반환하는 게 에러 없이 처리가 되는 지")
    void createPlayers() {
        // given
        // when
        // then
        Map<String, Integer> map = new HashMap<>();
        Assertions.assertThat(map.get("hello")).isNull();
    }
}
