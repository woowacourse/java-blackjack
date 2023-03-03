package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SimpleLinkedListTest {

    @Test
    void test1() {
        SimpleArrayList<Parent> list = new SimpleArrayList<>();

        list.add(new Parent("parent"));
        list.add(new Child("child"));

        Parent parent = list.get(0);
        Parent child = list.get(1);

        assertThat(parent.getName())
                .isEqualTo("parent");
        assertThat(child.getName())
                .isEqualTo("child");
    }
}
