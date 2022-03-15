package study;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EnumMapTest {

    private Map<Grade, Integer> gradeMap;

    private enum Grade {
        A, B, C, D, F
    }

    @BeforeEach
    void setUp() {
       gradeMap = new EnumMap<>(Grade.class);
    }

    @DisplayName("EnumMap은 처음에 완전히 빈 상태로 초기화된다.")
    @Test
    void init_isEmpty() {
        assertThat(gradeMap.keySet().size()).isEqualTo(0);
        assertThat(gradeMap.values().size()).isEqualTo(0);
    }

    @DisplayName("EnumMap에 추가된 순서와는 무관하게 Enum 자체의 순서에 따라 key들이 정렬된다.")
    @Test
    void order_followsEnumOrder() {
        gradeMap.put(Grade.B, 2);
        gradeMap.put(Grade.F, 5);
        gradeMap.put(Grade.A, 1);

        assertThat(gradeMap.keySet()).containsExactly(Grade.A, Grade.B, Grade.F);
    }

    @DisplayName("EnumMap에 추가되지 않은 key들은 아예 존재하지 않는다.")
    @Test
    void init() {
        gradeMap.put(Grade.B, 2);
        gradeMap.put(Grade.F, 5);
        gradeMap.put(Grade.A, 1);

        assertThat(gradeMap.keySet()).doesNotContain(Grade.C, Grade.D);
    }
}
