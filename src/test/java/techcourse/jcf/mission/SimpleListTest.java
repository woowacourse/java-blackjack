package techcourse.jcf.mission;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SimpleListTest {

    @Test
    public void simpleArrayListTest() {
        simpleListTest(new SimpleArrayList());
    }

    @Test
    public void simpleLinkedListTest() {
        simpleListTest(new SimpleLinkedList());
    }

    private void simpleListTest(SimpleList values) {
        assertThat(values.add("first")).isTrue();
        assertThat(values.add("second")).isTrue();
        assertDoesNotThrow(() -> values.add(0, "zero"));

        assertThat(values.size()).isEqualTo(3);
        assertThat(values.get(0)).isEqualTo("zero");
        assertThat(values.contains("first")).isTrue();
        assertThat(values.remove(0)).isEqualTo("zero");

        assertThat(values.size()).isEqualTo(2);
        assertThat(values.get(0)).isEqualTo("first");
        assertThat(values.contains("second")).isTrue();
        assertThat(values.remove("first")).isTrue();

        assertThat(values.size()).isEqualTo(1);
        assertThat(values.get(0)).isEqualTo("second");
        assertThat(values.set(0, "first")).isEqualTo("second");
        assertThat(values.indexOf("first")).isEqualTo(0);

        assertThat(values.isEmpty()).isFalse();
        values.clear();
        assertThat(values.isEmpty()).isTrue();

        values.clear();
        assertDoesNotThrow(() -> {
            for (int i = 0; i < 30; i++) {
                values.add(String.valueOf(i));
            }
        });
    }
}
