package techcourse.icf.mission;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimpleArrayListTest {

    @Test
    @DisplayName("미션1")
    void putTypeInteger() {
        SimpleList<Integer> values = new SimpleArrayList<Integer>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);

        Assertions.assertThat(first).isEqualTo(1);
        Assertions.assertThat(second).isEqualTo(2);
    }

    @Test
    @DisplayName("미션2")
    void genericMethod() {
        final String[] arrays = {"first", "second"};
        final SimpleList<String> values = SimpleList.<String>fromArrayToList(arrays);
        final SimpleList<String> values2 = new SimpleArrayList<>();
        values2.add("first");
        values2.add("second");
        Assertions.assertThat(values.size()).isEqualTo(values2.size());
        for (int i = 0; i < arrays.length; i++) {
            Assertions.assertThat(values.get(i)).isEqualTo(values2.get(i));
        }

    }
}