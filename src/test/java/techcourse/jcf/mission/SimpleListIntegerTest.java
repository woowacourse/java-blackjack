package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleListIntegerTest {
    private SimpleList<Integer> simpleList;

    @BeforeEach
    void initialize() {
        // 구현한 구현체로 바꾸기
        simpleList = new SimpleArrayList<>();
    }

    @Test
    void addAndGet() {
        simpleList.add(1);
        simpleList.add(2);
        assertThat(simpleList.get(0)).isEqualTo(1);
        assertThat(simpleList.get(1)).isEqualTo(2);
    }

    @Test
    void fromArrayToList() {
        SimpleList<Integer> simpleArrayList = SimpleList.<Integer>fromArrayToList(new Integer[]{1, 2});
        assertThat(simpleArrayList.get(0)).isEqualTo(1);
        assertThat(simpleArrayList.get(1)).isEqualTo(2);
    }
}