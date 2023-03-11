package study;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled("학습 테스트")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("collect(toMap())의 mergeFunctionTest")
public class ToMapMergeFunctionTest {

    @Test
    void 키의_충돌이_발생하면_앞의_엔트리의_값을_선택한다() {
        // given
        final List<SameNameSamePerson> sameNameSamePeople = List.of(
                new SameNameSamePerson("말랑", 22),
                new SameNameSamePerson("말랑", 23),
                new SameNameSamePerson("말랑", 24),
                new SameNameSamePerson("코다", 20),
                new SameNameSamePerson("코다", 21),
                new SameNameSamePerson("코다", 22)
        );

        // when
        final Map<SameNameSamePerson, Integer> collect = sameNameSamePeople.stream().collect(toMap(
                Function.identity(),
                person -> person.age,
                (a, b) -> b,
                HashMap::new
        ));

        // then
        assertThat(collect.get(new SameNameSamePerson("말랑", 100))).isEqualTo(24);
        assertThat(collect.get(new SameNameSamePerson("코다", 20))).isEqualTo(22);
    }

    @Test
    void 충돌이_발생하면_뒤의_엔트리의_값을_선택한다() {
        // given
        final List<SameNameSamePerson> sameNameSamePeople = List.of(
                new SameNameSamePerson("말랑", 22),
                new SameNameSamePerson("말랑", 23),
                new SameNameSamePerson("말랑", 24),
                new SameNameSamePerson("코다", 20),
                new SameNameSamePerson("코다", 21),
                new SameNameSamePerson("코다", 22)
        );

        // when
        final Map<SameNameSamePerson, Integer> collect = sameNameSamePeople.stream().collect(toMap(
                Function.identity(),
                person -> person.age,
                (a, b) -> a,
                HashMap::new
        ));

        // then
        assertThat(collect.get(new SameNameSamePerson("말랑", 100))).isEqualTo(22);
        assertThat(collect.get(new SameNameSamePerson("코다", 20))).isEqualTo(20);
    }

    @Test
    void 충돌이_발생하면_특정_로직을_수행한다() {
        // given
        final List<SameNameSamePerson> sameNameSamePeople = List.of(
                new SameNameSamePerson("말랑", 22),
                new SameNameSamePerson("말랑", 23),
                new SameNameSamePerson("말랑", 24),
                new SameNameSamePerson("코다", 20),
                new SameNameSamePerson("코다", 21),
                new SameNameSamePerson("코다", 22)
        );

        // when
        final Map<SameNameSamePerson, Integer> collect = sameNameSamePeople.stream().collect(toMap(
                Function.identity(),
                person -> person.age,
                (a, b) -> {
                    System.out.println(a + "와" + b + "사이 충돌이 발생해서 그냥 100살로 만들어 버립니다");
                    return 100;
                },
                HashMap::new
        ));

        // then
        assertThat(collect.get(new SameNameSamePerson("말랑", 100))).isEqualTo(100);
        assertThat(collect.get(new SameNameSamePerson("코다", 20))).isEqualTo(100);
    }

    @Test
    void 해시맵이_아니어도_충돌은_발생한다() {
        // given
        final List<TreeMapPerson> sameNameSamePeople = List.of(
                new TreeMapPerson("말랑", 22),
                new TreeMapPerson("말랑", 23),
                new TreeMapPerson("말랑", 24),
                new TreeMapPerson("코다", 20),
                new TreeMapPerson("코다", 21),
                new TreeMapPerson("코다", 22)
        );

        // when
        final Map<TreeMapPerson, Integer> collect = sameNameSamePeople.stream().collect(toMap(
                Function.identity(),
                person -> person.age,
                (a, b) -> {
                    System.out.println("충돌이 발생해서 그냥 100살로 만들어 버립니다");
                    return 100;
                },
                TreeMap::new
        ));

        // then
        assertThat(collect.get(new TreeMapPerson("말랑", 100))).isEqualTo(100);
        assertThat(collect.get(new TreeMapPerson("코다", 20))).isEqualTo(100);
    }

    static class TreeMapPerson implements Comparable<TreeMapPerson> {
        private String name;
        private int age;

        public TreeMapPerson(final String name, final int age) {
            this.name = name;
            this.age = age;
        }

        public String name() {
            return name;
        }

        public int age() {
            return age;
        }

        @Override
        public int compareTo(final TreeMapPerson o) {
            return name.compareTo(o.name);
        }
    }

    static class SameNameSamePerson {
        private String name;
        private int age;

        public SameNameSamePerson(final String name, final int age) {
            this.name = name;
            this.age = age;
        }

        public String name() {
            return name;
        }

        public int age() {
            return age;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof SameNameSamePerson)) return false;
            final SameNameSamePerson sameNameSamePerson = (SameNameSamePerson) o;
            return Objects.equals(name, sameNameSamePerson.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "SameNameSamePerson{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
