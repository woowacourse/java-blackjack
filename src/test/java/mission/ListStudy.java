package mission;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class ListStudy {

    @Test
    public void arrayList() {
        ArrayList<String> values = new ArrayList<>();
        values.add("first");
        values.add("second");

        assertThat(values.add("third")).isTrue();
        assertThat(values.size()).isEqualTo(3);
        assertThat(values.get(0)).isEqualTo("first");
        assertThat(values.contains("first")).isTrue();
        assertThat(values.remove(0)).isEqualTo("first");
        assertThat(values.size()).isEqualTo(2);
    }

    @Test
    public void simpleArrayListTest() {
        SimpleList values = new SimpleArrayList();

        values.add("first");
        values.add("second");

        assertThat(values.add("third")).isTrue();
        assertThat(values.size()).isEqualTo(3);
        assertThat(values.get(0)).isEqualTo("first");
        assertThat(values.contains("first")).isTrue();
        values.add(1, "new Second");
        assertThat(values.remove(1)).isEqualTo("new Second");
        assertThat(values.get(1)).isEqualTo("second");
        assertThat(values.size()).isEqualTo(3);
        values.clear();
        assertThat(values.isEmpty()).isTrue();
    }

    @Test
    public void simpleLinkedListTest() {
        SimpleList values = new SimpleLinkedList();

        values.add("first");
        values.add("second");

        assertThat(values.add("third")).isTrue();
        assertThat(values.size()).isEqualTo(3);
        assertThat(values.get(0)).isEqualTo("first");
        assertThat(values.contains("first")).isTrue();
        values.add(1, "new Second");
        assertThat(values.remove(1)).isEqualTo("new Second");
        assertThat(values.get(1)).isEqualTo("second");
        assertThat(values.size()).isEqualTo(3);
        values.add(values.size(), "last");
        assertThat(values.get(values.size() - 1)).isEqualTo("last");
        values.clear();
        assertThat(values.isEmpty()).isTrue();
    }
}
