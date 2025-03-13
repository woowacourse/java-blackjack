package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class HandTest {

    @Test
    @DisplayName("배팅 금액으로 객체를 생성한다")
    void 배팅_금액으로_객체를_생성한다() {
        assertThatNoException().isThrownBy(() -> new Hand(5_000));
    }

    @ParameterizedTest
    @CsvSource({
            "5_000, 1.5, 7_500",
            "5_000, -1.5, -7_500",
            "10_000, 1.5, 15_000",
            "10_000, 2.0, 20_000",
            "10_000, 2.5, 25_000",
    })
    @DisplayName("배수의 따라 계산해 반환한다")
    void 배수의_따라_계산해_반환한다(int batAmount, double multiple, int excepted) {
        Hand hand = new Hand(batAmount);

        int result = hand.calculateWinningAmount(multiple);

        assertThat(result).isEqualTo(excepted);
    }

    @ParameterizedTest
    @MethodSource("blackJackHandTestCases")
    @DisplayName("블랙잭인 경우 TRUE를 반환한다")
    void 블랙잭_여부를_반환한다(List<Card> cards, boolean excepted) {
        Hand hand = new Hand(0);
        hand.addCards(cards);

        boolean result = hand.isBlackJack();

        assertThat(result).isEqualTo(excepted);
    }

    private static Stream<Arguments> blackJackHandTestCases() {
        Card ace = new Card(CardNumber.ACE, CardShape.CLOVER);
        Card five = new Card(CardNumber.FIVE, CardShape.CLOVER);
        Card ten = new Card(CardNumber.TEN, CardShape.CLOVER);

        return Stream.of(
                Arguments.of(List.of(ace), false),
                Arguments.of(List.of(ace, five), false),
                Arguments.of(List.of(ace, five, five), false),
                Arguments.of(List.of(ace, ten), true)
        );
    }
}
