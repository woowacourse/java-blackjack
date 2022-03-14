package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.SIX;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardPattern.HEART;
import static blackjack.domain.card.CardPattern.SPADE;
import static blackjack.testutil.CardFixtureGenerator.createCards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    @Test
    @DisplayName("딜러의 카드가 17이상일 때 카드를 추가하면 예외가 발생해야 한다.")
    void hitExceptionByLimitDealerScore() {
        final List<Card> cards = createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN));
        final Participant dealer = Dealer.createNewDealer(cards);

        assertThatThrownBy(() -> dealer.hit(Card.of(SPADE, A)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 턴이 종료되어 카드를 더 받을 수 없습니다.");
    }

    @Test
    @DisplayName("딜러는 첫번째 카드 반환 시 1장의 카드만 반환해야 한다.")
    void firstCards() {
        final List<Card> cards = createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN));
        final Participant dealer = Dealer.createNewDealer(cards);

        assertThat(dealer.firstCards()).isEqualTo(Collections.singletonList(Card.of(SPADE, TEN)));
    }

    @Test
    @DisplayName("딜러가 게임을 직접 종료하려하면 예외가 발생해야 한다.")
    void changeFinishStatusException() {
        final List<Card> cards = createCards(Card.of(SPADE, TWO), Card.of(SPADE, SEVEN));
        final Participant dealer = Dealer.createNewDealer(cards);

        assertThatThrownBy(() -> dealer.changeFinishStatus())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러는 직접 게임을 종료할 권한이 없습니다.");
    }

    @ParameterizedTest(name = "{index} : {1}")
    @MethodSource("createCardHit")
    @DisplayName("딜러의 hit 가능여부를 반환할 수 있다.")
    void canHit(final List<Card> cards, final boolean expected) {
        final Participant dealer = Dealer.createNewDealer(cards);
        assertThat(dealer.canHit()).isEqualTo(expected);
    }

    private static Stream<Arguments> createCardHit() {
        return Stream.of(
                Arguments.of(Arrays.asList(Card.of(SPADE, TEN), Card.of(SPADE, SIX)), true),
                Arguments.of(Arrays.asList(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN)), false)
        );
    }

    @Test
    @DisplayName("종료되지 않은 딜러가 모든 카드를 반환하려고 하는 경우 예외가 발생해야 한다.")
    void getCardsException() {
        final List<Card> cards = createCards(Card.of(SPADE, TEN), Card.of(SPADE, SIX));
        final Dealer dealer = Dealer.createNewDealer(cards);

        assertThatThrownBy(() -> dealer.cards())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러는 턴이 종료되지 않을 때 모든 카드를 반환할 수 없습니다.");
    }

    @Test
    @DisplayName("턴이 종료되지 않았는데 스코어를 반환하려고 하면 예외를 발생시킨다.")
    void calculateResultScoreExceptionByNotEndTurn() {
        final List<Card> cards = createCards(Card.of(SPADE, TEN), Card.of(SPADE, TWO));
        final Dealer dealer = Dealer.createNewDealer(cards);

        assertThatThrownBy(() -> dealer.calculateResultScore())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("턴이 종료되지 않아 카드의 합을 계산할 수 없습니다.");
    }

    @Test
    @DisplayName("딜러는 본인이 가지는 최대 점수를 반환한다.")
    void calculateResultScore() {
        final List<Card> cards = createCards(Card.of(SPADE, A), Card.of(HEART, A));
        final Dealer dealer = Dealer.createNewDealer(cards);

        assertThat(dealer.calculateResultScore()).isEqualTo(22);
    }
}
