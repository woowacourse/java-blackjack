package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SimpleArrayListGenericTest {


    @Test
    void testAddString() {
        SimpleArrayList<String> simpleArrayList = new SimpleArrayList<>();

        simpleArrayList.add("폴로");

        assertThat(simpleArrayList.get(0)).isEqualTo("폴로");
    }

    @Test
    void testAddInteger() {
        SimpleArrayList<Integer> simpleArrayList = new SimpleArrayList<>();

        simpleArrayList.add(10);

        assertThat(simpleArrayList.get(0)).isEqualTo(10);
    }

    @Test
    void set() {
        SimpleArrayList<String> simpleArrayList = new SimpleArrayList<>();
        simpleArrayList.add("폴로");
        simpleArrayList.add("마코");

        simpleArrayList.set(1, "mako");

        assertThat(simpleArrayList.get(1)).isEqualTo("mako");
    }

    @Test
    void indexOfString() {
        SimpleArrayList<String> simpleArrayList = new SimpleArrayList<>();
        simpleArrayList.add("폴로");
        simpleArrayList.add("마코");

        assertThat(simpleArrayList.indexOf("폴로")).isEqualTo(0);
    }

    @Test
    void size() {
        SimpleArrayList<String> simpleArrayList = new SimpleArrayList<>();
        simpleArrayList.add("폴로");
        simpleArrayList.add("마코");

        assertThat(simpleArrayList.size()).isEqualTo(2);
    }

    @Test
    void isEmpty() {
        SimpleArrayList<String> simpleArrayList = new SimpleArrayList<>();

        assertThat(simpleArrayList.isEmpty()).isTrue();
    }

    @Test
    void removeByValue() {
        SimpleArrayList<Integer> integerSimpleArrayList = new SimpleArrayList<>();
        integerSimpleArrayList.add(1);
        integerSimpleArrayList.add(3);
        integerSimpleArrayList.add(2);

        integerSimpleArrayList.remove(Integer.valueOf(3));

        assertThat(integerSimpleArrayList.contains(3)).isFalse();
    }

    @Test
    void removeByIndex() {
        SimpleArrayList<Integer> integerSimpleArrayList = new SimpleArrayList<>();
        integerSimpleArrayList.add(1);
        integerSimpleArrayList.add(3);
        integerSimpleArrayList.add(2);

        integerSimpleArrayList.remove(1);

        assertThat(integerSimpleArrayList.contains(3)).isFalse();
    }

    @Test
    void clear() {
        SimpleArrayList<Integer> integerSimpleArrayList = new SimpleArrayList<>();
        integerSimpleArrayList.add(1);
        integerSimpleArrayList.add(3);
        integerSimpleArrayList.add(2);

        integerSimpleArrayList.clear();

        assertThat(integerSimpleArrayList.isEmpty()).isTrue();
    }
}
