package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NumberTest {

    @DisplayName("카드의 숫자는 1부터 k까지 14개다.")
    @Test
    void cardNumber() {
        //given

        //when
        Number[] values = Number.values();

        //then
        assertThat(values).hasSize(14);
    }

    @DisplayName("카드의 숫자는 1부터 10까지의 점수를 가진다.")
    @Test
    void cardNumberPoint() {
        //given
        Number five = Number.FIVE;

        //when

        //then
        assertThat(five.getPoint()).isEqualTo(5);
    }

    @DisplayName("J, Q, K 는 각각 10으로 계산한다.")
    @ParameterizedTest
    @MethodSource("provideJQK")
    void cardNumberPointForJQK(Number number) {
        //given

        //when

        //then
        assertThat(number.getPoint()).isEqualTo(10);
    }

    public static Stream<Arguments> provideJQK() {
        return Stream.of(Arguments.of(Number.JACK, Number.QUEEN, Number.KING));
    }

}
