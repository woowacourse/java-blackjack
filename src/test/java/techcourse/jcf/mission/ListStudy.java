package techcourse.jcf.mission;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ListStudy {

    @Test
    public void arrayList() {
        // MISSION 1
        SimpleList<Integer> list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        assertThat(list.get(0)).isSameAs(1);
        assertThat(list.get(1)).isSameAs(2);


        // MISSION 2
        final String[] arrays = {"first", "second"};
        final SimpleList<String> values = SimpleList.<String>fromArrayToList(arrays);
        assertThat(values.get(0)).isEqualTo("first");
        assertThat(values.get(1)).isEqualTo("second");

        // MISSION 3
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        assertThat(doubleValues.get(0)).isEqualTo(0.5D);
        assertThat(doubleValues.get(1)).isEqualTo(0.7D);
        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        assertThat(doubleTotal).isEqualTo(1.2d);


        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);
        assertThat(intValues.get(0)).isEqualTo(1);
        assertThat(intValues.get(1)).isEqualTo(2);
        final double intTotal = SimpleList.sum(intValues);  // 3
        assertThat(intTotal).isEqualTo(3);

        // MISSION 4
        final SimpleList<Double> doubleValues2 = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues2);
        assertThat(filteredDoubleValues.size()).isSameAs(1);
        assertThat(filteredDoubleValues.get(0)).isEqualTo(-0.1);


        final SimpleList<Integer> intValues2 = new SimpleArrayList<Integer>(-10, 1, 2);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues2);
        assertThat(filteredIntValues.size()).isSameAs(1);
        assertThat(filteredIntValues.get(0)).isEqualTo(-10);

    }

}
