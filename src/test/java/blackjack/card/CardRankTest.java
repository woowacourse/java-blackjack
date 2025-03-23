package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardRankTest {

    public static Stream<Arguments> provideJQK() {
        return Stream.of(Arguments.of(CardRank.JACK, CardRank.QUEEN, CardRank.KING));
    }

    @DisplayName("카드의 숫자는 1부터 k까지 13개다.")
    @Test
    void cardNumber() {
        //given

        //when
        CardRank[] values = CardRank.values();

        //then
        assertThat(values).hasSize(13);
    }

    @DisplayName("카드의 숫자는 1부터 10까지의 점수를 가진다.")
    @Test
    void cardNumberPoint() {
        //given
        CardRank five = CardRank.FIVE;

        //when
        int actual = five.getPoint();

        //then
        assertThat(actual).isEqualTo(5);
    }

    @DisplayName("J, Q, K 는 각각 10으로 계산한다.")
    @ParameterizedTest
    @MethodSource("provideJQK")
    void cardNumberPointForJQK(CardRank cardRank) {
        //given //when
        int actual = cardRank.getPoint();
        //then
        assertThat(cardRank.getPoint()).isEqualTo(10);
    }

}
