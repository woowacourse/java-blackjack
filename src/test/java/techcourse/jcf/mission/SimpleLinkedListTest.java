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

    @Test
    void test4() {
        // given
        SimpleList<Double> list1 = new SimpleArrayList<>(-0.1, 0.5, 0.7);
        SimpleList<Integer> list2 = new SimpleArrayList<>(-10, 1, 2);

        // when
        SimpleList<Double> doubleSimpleList = SimpleList.filterNegative(list1);
        SimpleList<Integer> integerSimpleList = SimpleList.filterNegative(list2);

        // then
        assertThat(doubleSimpleList.get(0))
                .isEqualTo(0.5);
        assertThat(doubleSimpleList.get(1))
                .isEqualTo(0.7);
        assertThat(integerSimpleList.get(0))
                .isEqualTo(1);
        assertThat(integerSimpleList.get(1))
                .isEqualTo(2);
    }

    @Test
    void test5() {
        // given
        Child child = new Child("child");
        SimpleArrayList<Parent> parentList = new SimpleArrayList<>();
        SimpleArrayList<Child> childList = new SimpleArrayList<>(child);
        SimpleArrayList<Number> numberList = new SimpleArrayList<>();
        SimpleArrayList<Integer> intList = new SimpleArrayList<>(1);

        // when
        SimpleList.copy(childList, parentList);
        SimpleList.copy(intList, numberList);

        // then
        assertThat(parentList.get(0))
                .isEqualTo(child);
        assertThat(numberList.get(0))
                .isEqualTo(1);
    }
}
