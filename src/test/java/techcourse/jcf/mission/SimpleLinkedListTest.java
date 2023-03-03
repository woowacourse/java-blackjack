package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SimpleLinkedListTest {

    @Test
    void test1() {
        // given
        SimpleArrayList<Parent> list = new SimpleArrayList<>();
        list.add(new Parent("parent"));
        list.add(new Child("child"));

        // when
        Parent parent = list.get(0);
        Parent child = list.get(1);

        // then
        assertThat(parent.getName())
                .isEqualTo("parent");
        assertThat(child.getName())
                .isEqualTo("child");
    }

    @Test
    void test2() {
        // given
        SimpleList<Parent> list = SimpleList.fromArrayToList(
                new Parent[]{new Parent("parent"), new Child("child")});

        // when
        Parent parent = list.get(0);
        Parent child = list.get(1);

        // then
        assertThat(parent.getName())
                .isEqualTo("parent");
        assertThat(child.getName())
                .isEqualTo("child");
    }

    @Test
    void test3() {
        // given
        SimpleList<Double> list1 = new SimpleArrayList<>(0.5, 0.7);
        SimpleList<Integer> list2 = new SimpleArrayList<>(1, 2);

        // when
        double doubleTotal = SimpleList.sum(list1);
        double intTotal = SimpleList.sum(list2);

        // then
        assertThat(doubleTotal)
                .isEqualTo(1.2);
        assertThat(intTotal)
                .isEqualTo(3.0);
    }
}
