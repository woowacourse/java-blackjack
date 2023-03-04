package techcourse.jcf.mission;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class SimpleArrayListTest {

    @Test
    @DisplayName("미션1")
    void mission1() {
        //given
        SimpleList<Integer> values = new SimpleArrayList<>();

        //when
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);

        //then
        assertThat(first).isEqualTo(1);
        assertThat(second).isEqualTo(2);
    }

    @Test
    @DisplayName("미션2")
    void mission2() {
        //given
        final String[] arrays = {"first", "second"};


        //when
        final SimpleList<String> values = SimpleArrayList.<String>fromArrayToList(arrays);

        //then
        assertThat(values.get(0)).isEqualTo(arrays[0]);
        assertThat(values.get(1)).isEqualTo(arrays[1]);
    }

    @Test
    @DisplayName("미션3")
    void mission3() {
        //given
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

        //when
        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3

        //then
        assertThat(doubleTotal).isEqualTo(1.2);
        assertThat(intTotal).isEqualTo(3);
    }

    @Test
    @DisplayName("미션4")
    void mission4() {
        //given
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, 2);

        //when
        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);

        //then
        assertThat(filteredDoubleValues.size()).isEqualTo(2);

        assertThat(filteredIntValues.size()).isEqualTo(2);
    }
}
