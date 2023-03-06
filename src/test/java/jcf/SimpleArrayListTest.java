package jcf;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.ThrowableAssert.*;

public class SimpleArrayListTest {
    @Nested
    class 추가 {
        @Test
        void should_원소가추가된다_when_add_T호출() {
            // given
            SimpleList<Integer> simpleList = new SimpleArrayList<>();
            for (int i = 0; i < 10; i++) {
                simpleList.add(i);
            }

            // when
            simpleList.add(10);

            // then
            assertThat(simpleList.get(10)).isEqualTo(10);
        }

        @ParameterizedTest
        @CsvSource({"0,-1", "10,1", "5,5"})
        void should_원소가추가된다_when_add_index_T호출(int index, int value) {
            // given
            SimpleList<Integer> simpleList = new SimpleArrayList<>();
            for (int i = 0; i < 10; i++) {
                simpleList.add(i);
            }

            // when
            simpleList.add(index, value);

            // then
            assertThat(simpleList.get(index)).isEqualTo(value);
        }

        @ParameterizedTest
        @CsvSource({"-1,0", "11,2"})
        void should_예외를던진다_when_add시Index가범위를벗어날때(int index, int value) {
            // given
            SimpleList<Integer> simpleList = new SimpleArrayList<>();
            for (int i = 0; i < 10; i++) {
                simpleList.add(i);
            }

            // when
            ThrowingCallable throwingCallable = () -> simpleList.add(index, value);

            // then
            assertThatThrownBy(throwingCallable)
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }
    }
}
