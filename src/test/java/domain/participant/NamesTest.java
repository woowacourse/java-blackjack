package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NamesTest {
    @Test
    @DisplayName("플레이어 인원 테스트(6명이상)")
    void validatePlayerNumbersOverSixTest() {
        List<String> names = List.of("pobi", "lisa", "jamie", "malone", "gugu", "jackson", "kanye");

        assertThatThrownBy(() -> new Names(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어 인원은 1~6명 입니다.");
    }

    @Test
    @DisplayName("플레이어 인원 테스트(0명)")
    void validatePlayerNumbersEqualZeroTest() {
        List<String> names = Collections.emptyList();

        assertThatThrownBy(() -> new Names(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어 인원은 1~6명 입니다.");
    }

}
