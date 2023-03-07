package techcourse.jcf.mission;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MissionTest {

    @Test
    void mission_1() {
        SimpleList<Integer> values = new SimpleArrayList<>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);

        assertAll(
                () -> assertThat(first).isEqualTo(1),
                () -> assertThat(second).isEqualTo(2)
        );
    }

    @Test
    void mission_2() {
        final String[] arrays = {"first", "second"};

        final SimpleList<String> values = SimpleList.<String>fromArrayToList(arrays);

        assertAll(
                () -> assertThat(values.get(0)).isEqualTo(arrays[0]),
                () -> assertThat(values.get(1)).isEqualTo(arrays[1]),
                () -> assertThat(values.size()).isEqualTo(2)
        );

    }
}
