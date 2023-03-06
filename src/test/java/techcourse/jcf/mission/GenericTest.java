package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GenericTest {
    @Test
    void mission1() {
        SimpleList<Integer> values = new SimpleArrayList<>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);

        assertThat(first).isEqualTo(1);
        assertThat(second).isEqualTo(2);
    }
}
