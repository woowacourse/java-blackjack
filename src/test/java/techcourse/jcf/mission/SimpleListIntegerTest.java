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

    @Test
    void sum() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

        assertThat(SimpleList.sum(doubleValues)).isEqualTo(1.2);
        assertThat(SimpleList.sum(intValues)).isEqualTo(3);
    }

    @Test
    void sumThrowException() {
        final SimpleList<String> stringValues = new SimpleArrayList<String>("1", "2");
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

//        assertThat(SimpleList.sum(stringValues)).isEqualTo(3); // Checked exception 발생
    }
}