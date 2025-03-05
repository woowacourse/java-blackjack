package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    @DisplayName("합이 16이하인지 판단한다")
    @ParameterizedTest
    @MethodSource("createDrawCase")
    void test1(List<Card> inputCard, boolean expected) {
        //given
        Cards cards = Cards.of(inputCard);
        Dealer dealer = Dealer.of(cards);
        //when
        boolean actual = dealer.hasToDraw();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream createDrawCase() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(CardNumber.A, CardShape.CLOVER), new Card(CardNumber.TWO, CardShape.CLOVER)),
                        true),
                Arguments.of(List.of(new Card(CardNumber.SEVEN, CardShape.CLOVER),
                        new Card(CardNumber.KING, CardShape.CLOVER)), false)
        );
    }
}