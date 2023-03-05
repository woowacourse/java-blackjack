package techcourse.generic.mission;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Mission {

    @Test
    void mission1() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);

        Integer first = list.get(0);
        Integer second = list.get(1);

        Assertions.assertThat(first).isEqualTo(1);
        Assertions.assertThat(second).isEqualTo(2);
    }

    @Test
    void mission2() {
        final String[] arrays = {"first", "second"};

        SimpleArrayList<String> list = SimpleArrayList.<String>fromArrayToList(arrays);

        Assertions.assertThat(list.get(0)).isEqualTo("first");
        Assertions.assertThat(list.get(1)).isEqualTo("second");
    }

    @Test
    void mission3() {
        SimpleArrayList<Double> doubles = new SimpleArrayList<>(0.5, 0.7);
        SimpleArrayList<Integer> integers = new SimpleArrayList<>(1, 2);

        Assertions.assertThat(SimpleArrayList.sum(doubles)).isEqualTo(1.2);
        System.out.println(SimpleArrayList.sum(integers));
        Assertions.assertThat(SimpleArrayList.sum(integers)).isEqualTo(3);
    }
}
