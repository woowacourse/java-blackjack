package blackjack.state.started.finished;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.card.Card;
import blackjack.card.CardRank;
import blackjack.card.CardSymbol;
import fixture.HandFixture;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FinishedTest {

    public static Stream<Arguments> provideInstancesOfFinished() {
        return Stream.of(
                Arguments.of(
                        new Blackjack(HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.ACE),
                                Card.of(CardSymbol.SPADE, CardRank.JACK))),
                        new Stay(HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.KING),
                                Card.of(CardSymbol.SPADE, CardRank.JACK))),
                        new Bust(HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.ACE),
                                Card.of(CardSymbol.SPADE, CardRank.JACK)))
                ));
    }

    public static Stream<Arguments> provideInstancesOfFinishedWithExpected() {
        return Stream.of(
                Arguments.of(
                        new Blackjack(HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.ACE),
                                Card.of(CardSymbol.SPADE, CardRank.JACK))),
                        BigDecimal.valueOf(1500)
                ),
                Arguments.of(
                        new Stay(HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.KING),
                                Card.of(CardSymbol.SPADE, CardRank.JACK))),
                        BigDecimal.valueOf(1000)
                ),
                Arguments.of(
                        new Bust(HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.ACE),
                                Card.of(CardSymbol.SPADE, CardRank.JACK))),
                        BigDecimal.valueOf(1000)
                ));
    }

    @DisplayName("Finished 상태에서 hit을 시도하면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideInstancesOfFinished")
    void hit(Finished finishedState) {
        //given
        Card card = Card.of(CardSymbol.SPADE, CardRank.EIGHT);

        // when // then
        assertThatCode(() -> finishedState.hit(card))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @DisplayName("Finished 상태에서 stay를 시도하면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideInstancesOfFinished")
    void stay(Finished finishedState) {

        // when // then
        assertThatCode(finishedState::stay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @DisplayName("Finished 상태에서 isFinished는 true이다.")
    @ParameterizedTest
    @MethodSource("provideInstancesOfFinished")
    void isFinished(Finished finishedState) {

        // when
        boolean actual = finishedState.isFinished();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("Finished 상태에서 getProfit은 구현체에 맞는 수익을 반환한다.")
    @ParameterizedTest
    @MethodSource("provideInstancesOfFinishedWithExpected")
    void getProfit(Finished finishedState, BigDecimal expected) {
        //given
        BigDecimal bettingAmount = BigDecimal.valueOf(1000);

        // when
        BigDecimal actual = finishedState.getProfit(bettingAmount);

        // then
        assertThat(actual.compareTo(expected)).isZero();
    }

}
