package techcourse.generic.mission;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Mission1 {

    @Test
    void test() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);

        Integer first = list.get(0);
        Integer second = list.get(1);

        Assertions.assertThat(first).isEqualTo(1);
        Assertions.assertThat(second).isEqualTo(2);
    }
}
