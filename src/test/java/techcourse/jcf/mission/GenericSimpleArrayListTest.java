package techcourse.jcf.mission;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GenericSimpleArrayListTest {

    @DisplayName("Integer 값을 넣어도 리스트가 동작한다.")
    @Test
    void IntegerArrayListTest() {
        SimpleList<Integer> values = new SimpleArrayList<Integer>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);
        assertThat(first).isEqualTo(1);
        assertThat(second).isEqualTo(2);
    }

    @DisplayName("배열을 받아 SimpleList로 변환하는 메서드를 구현한다.")
    @Test
    void fromArrayToListTest() {
        final String[] arrays = {"first", "second"};

        final SimpleList<String> values = SimpleList.<String>fromArrayToList(arrays);
    }
}
