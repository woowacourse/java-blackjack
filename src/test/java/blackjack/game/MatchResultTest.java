package blackjack.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Rank;
import blackjack.card.Shape;
import blackjack.player.Hand;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MatchResultTest {

    static Hand handBlackJack = new Hand(List.of(
            new Card(Shape.CLOVER, Rank.ACE),
            new Card(Shape.DIAMOND, Rank.TEN)
    ));
    static Hand hand22 = new Hand(List.of(
            new Card(Shape.HEART, Rank.TEN),
            new Card(Shape.CLOVER, Rank.TEN),
            new Card(Shape.DIAMOND, Rank.TWO)
    ));
    static Hand hand21 = new Hand(List.of(
            new Card(Shape.CLOVER, Rank.JACK),
            new Card(Shape.DIAMOND, Rank.TEN),
            new Card(Shape.HEART, Rank.ACE)
    ));
    static Hand hand20 = new Hand(List.of(
            new Card(Shape.CLOVER, Rank.JACK),
            new Card(Shape.DIAMOND, Rank.TEN)
    ));

    @ParameterizedTest
    @MethodSource("playerWinCases")
    @DisplayName("플레이어가 배팅 금액만큼 돈을 버는 경우를 올바르게 판단한다.")
    void playerWinTest(Hand playerHand, Hand dealerHand) {
        double result = MatchResult.calculateRateOfPrize(playerHand, dealerHand);

        assertThat(result).isEqualTo(MatchResult.PLAYER_WIN.getRateOfPrize());
    }

    static Stream<Arguments> playerWinCases() {
        return Stream.of(
                Arguments.of(hand20, hand22),
                Arguments.of(hand21, hand20)
        );
    }

    @ParameterizedTest
    @MethodSource("playerBlackJackCases")
    @DisplayName("플레이어가 배팅 금액의 1.5배만큼 돈을 버는 경우를 올바르게 판단한다.")
    void playerBlackJackTest(Hand playerHand, Hand dealerHand) {
        double result = MatchResult.calculateRateOfPrize(playerHand, dealerHand);

        assertThat(result).isEqualTo(MatchResult.PLAYER_BLACKJACK.getRateOfPrize());
    }

    static Stream<Arguments> playerBlackJackCases() {
        return Stream.of(
                Arguments.of(handBlackJack, hand22),
                Arguments.of(handBlackJack, hand21),
                Arguments.of(handBlackJack, hand20)
        );
    }

    @ParameterizedTest
    @MethodSource("dealerWinCases")
    @DisplayName("플레이어가 배팅 금액을 잃는 경우를 올바르게 판단한다.")
    void dealerWinTest(Hand playerHand, Hand dealerHand) {
        double result = MatchResult.calculateRateOfPrize(playerHand, dealerHand);

        assertThat(result).isEqualTo(MatchResult.DEALER_WIN.getRateOfPrize());
    }

    static Stream<Arguments> dealerWinCases() {
        return Stream.of(
                Arguments.of(hand21, handBlackJack),
                Arguments.of(hand22, hand20),
                Arguments.of(hand20, hand21)
        );
    }

    @ParameterizedTest
    @MethodSource("tieCases")
    @DisplayName("플레이어가 배팅 금액을 돌려받는 경우를 올바르게 판단한다.")
    void tieTest(Hand playerHand, Hand dealerHand) {
        double result = MatchResult.calculateRateOfPrize(playerHand, dealerHand);

        assertThat(result).isEqualTo(MatchResult.TIE.getRateOfPrize());
    }

    static Stream<Arguments> tieCases() {
        return Stream.of(
                Arguments.of(handBlackJack, handBlackJack),
                Arguments.of(hand21, hand21),
                Arguments.of(hand20, hand20)
        );
    }
}
