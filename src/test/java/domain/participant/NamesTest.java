package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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

    @Test
    @DisplayName("플레이어 중복 참가 테스트")
    void validateIsDuplicateTest() {
        List<String> names = List.of("pobi", "pobi");

        assertThatThrownBy(() -> new Names(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 중복된 이름의 플레이어가 게임에 참여할 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어 이름 생성 테스트")
    void nameTest() {
        List<String> names = List.of("pobi", "lisa");
        assertDoesNotThrow(() -> new Names(names));
    }

}
