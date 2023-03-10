package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NameTest {
    @Nested
    class 생성 {
        @ParameterizedTest
        @CsvSource(value = {"'':이름은 공백일 수 없습니다.", "5자넘는이름:이름은 1 ~ 5자입니다.",
                "' as ':이름의 양쪽에 공백이 들어갈 수 없습니다."}, delimiter = ':')
        void should_예외를던진다_when_적절하지않은이름으로생성(String inputName, String expectedErrorMessage) {
            //given

            //when
            ThrowingCallable throwingCallable = () -> new Name(inputName);

            //then
            assertThatThrownBy(throwingCallable)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(expectedErrorMessage);
        }
    }

}