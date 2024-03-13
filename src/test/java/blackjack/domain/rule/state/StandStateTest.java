package blackjack.domain.rule.state;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardNumber.THREE;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardShape.DIA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Hands;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StandStateTest {

    static final State score19count2 = new StandState(new Hands(List.of(Card.of(TEN, DIA), Card.of(NINE, DIA))));
    static final State score20count2 = new StandState(new Hands(List.of(Card.of(TEN, DIA), Card.of(JACK, DIA))));
    static final State score21count2 = new BlackjackState(new Hands(List.of(Card.of(ACE, DIA), Card.of(JACK, DIA))));
    static final State score21count3 = new StandState(
            new Hands(List.of(Card.of(ACE, DIA), Card.of(JACK, DIA), Card.of(QUEEN, DIA))));
    static final State score22count3 = new BurstState(
            new Hands(List.of(Card.of(TEN, DIA), Card.of(JACK, DIA), Card.of(TWO, DIA))));
    static final State score23count3 = new BurstState(
            new Hands(List.of(Card.of(TEN, DIA), Card.of(JACK, DIA), Card.of(THREE, DIA))));

    @DisplayName("Stand 상태에서 카드를 뽑을 수 없다.")
    @Test
    void draw() {
        final Card card = Card.of(CardNumber.ACE, DIA);

        assertThatThrownBy(() -> score20count2.draw(card))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Stand 상태에서 stand를 호출할 수 없다.")
    @Test
    void stand() {
        assertThatThrownBy(score20count2::stand)
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("딜러 카드 상태가 Burst이면 WIN이다.")
    @Test
    void calculateBetLeverageBurst() {
        // given
        final State dealerState = new BurstState(new Hands(List.of(
                Card.of(TEN, DIA),
                Card.of(CardNumber.QUEEN, DIA),
                Card.of(CardNumber.TWO, DIA)
        )));

        // when
        final BetLeverage betLeverage = score20count2.calculateBetLeverage(dealerState);

        // then
        assertThat(betLeverage).isEqualTo(BetLeverage.WIN);
    }

    @DisplayName("딜러와 참여자의 카드들로 배팅결과를 계산한다.")
    @ParameterizedTest(name = "딜러 카드 : {0}, 플레이어 카드 : {1}, 결과 : {2}")
    @MethodSource("handsAndWinStatus")
    void calculateBetLeverage(State dealerState, State playerState, BetLeverage expected) {
        final BetLeverage betLeverage = playerState.calculateBetLeverage(dealerState);

        assertThat(betLeverage).isEqualTo(expected);
    }

    static Stream<Arguments> handsAndWinStatus() {
        return Stream.of(                                                   // 딜러(카드수) | 참여자(카드수) | 레버리지
                arguments(score20count2, score21count3, BetLeverage.WIN),   // 생존(2)    , 블랙잭(3)     , 1
                arguments(score23count3, score21count3, BetLeverage.WIN),   // 죽음(3)    , 블랙잭(3)     , 1
                arguments(score23count3, score20count2, BetLeverage.WIN),   // 죽음(3)    , 생존(2)      , 1
                arguments(score19count2, score20count2, BetLeverage.WIN),   // 생존(2)    , 생존(2)      , 1
                arguments(score21count2, score20count2, BetLeverage.LOSE),  // 블랙잭(2)   , 생존(2)      , -1
                arguments(score21count2, score21count3, BetLeverage.LOSE),  // 블랙잭(2)   , 블랙잭(3)     , -1
                arguments(score20count2, score19count2, BetLeverage.LOSE),  // 생존(2)    , 생존(2)       , -1
                arguments(score21count3, score21count3, BetLeverage.TIE),   // 블랙잭(3)   , 블랙잭(3)     , 0
                arguments(score20count2, score20count2, BetLeverage.TIE)    // 생존(2)    , 생존(2)       , 0
        );
    }
}
