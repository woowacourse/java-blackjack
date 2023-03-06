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

    }

}
