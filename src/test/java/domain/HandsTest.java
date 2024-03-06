package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class HandsTest {

    @Test
    @DisplayName("카드를 가지고 있는 객체를 생성한다.")
    void createPacket() {
        Assertions.assertThatCode(Hands::createEmptyPacket)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드를 추가한다.")
    void addCard() {
        //given
        final Hands hands = Hands.createEmptyPacket();

        //when
        hands.add(new Card(CardNumber.EIGHT, CardShape.CLOVER));

        //then
        Assertions.assertThat(hands.size()).isEqualTo(1);
    }

    @DisplayName("카드의 합을 구한다.")
    @ParameterizedTest
    @MethodSource("sumParameterProvider")
    void sum(final Hands hands, final int expected) {
        // given & when
        final int result = hands.sum();

        // then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    //TODO: 디스플레이네임 수정 필요, 좀더 명확하고, 이해하기 쉽게
    // 코드 중복이 발생한다.
    @DisplayName("에이스를 11로 계산한다.")
    @ParameterizedTest
    @MethodSource("sumAce11ParameterProvider")
    void sumAce11(final Hands hands, final int expected) {
        // given & when
        final int result = hands.sum();

        // then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @DisplayName("에이스를 1로 계산한다.")
    @ParameterizedTest
    @MethodSource("sumAce1ParameterProvider")
    void sumAce1(final Hands hands, final int expected) {
        // given & when
        final int result = hands.sum();

        // then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> sumParameterProvider() {
        return Stream.of(
                Arguments.of(new Hands(List.of(new Card(CardNumber.TWO, CardShape.HEART),
                                new Card(CardNumber.EIGHT, CardShape.SPADE),
                                new Card(CardNumber.JACK, CardShape.CLOVER))),
                        20),
                Arguments.of(new Hands(List.of(new Card(CardNumber.THREE, CardShape.DIAMOND),
                                new Card(CardNumber.NINE, CardShape.CLOVER),
                                new Card(CardNumber.NINE, CardShape.CLOVER))),
                        21)
        );
    }

    static Stream<Arguments> sumAce11ParameterProvider() {
        return Stream.of(
                Arguments.of(new Hands(List.of(new Card(CardNumber.ACE, CardShape.HEART),
                                new Card(CardNumber.EIGHT, CardShape.SPADE),
                                new Card(CardNumber.TWO, CardShape.CLOVER))),
                        21),
                Arguments.of(new Hands(List.of(new Card(CardNumber.ACE, CardShape.DIAMOND),
                                new Card(CardNumber.TWO, CardShape.CLOVER),
                                new Card(CardNumber.FOUR, CardShape.CLOVER),
                                new Card(CardNumber.TWO, CardShape.CLOVER))),
                        19)
        );
    }

    static Stream<Arguments> sumAce1ParameterProvider() {
        return Stream.of(
                Arguments.of(new Hands(List.of(new Card(CardNumber.ACE, CardShape.HEART),
                                new Card(CardNumber.NINE, CardShape.SPADE),
                                new Card(CardNumber.NINE, CardShape.CLOVER))),
                        19),
                Arguments.of(new Hands(List.of(new Card(CardNumber.ACE, CardShape.DIAMOND),
                                new Card(CardNumber.EIGHT, CardShape.CLOVER),
                                new Card(CardNumber.FIVE, CardShape.CLOVER),
                                new Card(CardNumber.SIX, CardShape.CLOVER))),
                        20)
        );
    }
}
