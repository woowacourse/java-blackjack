package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Deck;
import blackjack.domain.rule.state.StandState;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class DealerTest {

    @DisplayName("딜러의 턴 진행 시나리오")
    @TestFactory
    Collection<DynamicTest> playTurnHit() {
        // given
        final User dealer = new Dealer();
        final Deck deck = new Deck(
                List.of(Card.of(CardNumber.TEN, CardShape.DIA),
                        Card.of(CardNumber.SIX, CardShape.DIA),
                        Card.of(CardNumber.ACE, CardShape.DIA),
                        Card.of(CardNumber.ACE, CardShape.SPADE)));

        return List.of(
                DynamicTest.dynamicTest("딜러는 생성시 초기 상태이다.", () -> {
                    assertThat(dealer.getState().isInit()).isTrue();
                }),
                DynamicTest.dynamicTest("딜러는 초기 상태에서 1장 받을 수 있고 초기상태다.", () -> {
                    // when
                    dealer.playTurn(deck);

                    // then
                    assertAll(
                            () -> assertThat(dealer.getHands().getCards())
                                    .containsExactly(Card.of(CardNumber.TEN, CardShape.DIA)),
                            () -> assertThat(dealer.getState().isInit()).isTrue()
                    );
                }),
                DynamicTest.dynamicTest("딜러는 1장받은 상태에서 1장 더 받으면 초기상태가 아니다.", () -> {
                    // when
                    dealer.playTurn(deck);

                    // then
                    assertAll(
                            () -> assertThat(dealer.getHands().getCards())
                                    .containsExactly(Card.of(CardNumber.TEN, CardShape.DIA),
                                            Card.of(CardNumber.SIX, CardShape.DIA)),
                            () -> assertThat(dealer.getState().isInit()).isFalse()
                    );
                }),
                DynamicTest.dynamicTest("딜러는 카드 점수가 16이하이면 카드를 더 받는다.", () -> {
                    // when
                    dealer.playTurn(deck);

                    // then
                    assertThat(dealer.getHands().getCards())
                            .containsExactly(Card.of(CardNumber.TEN, CardShape.DIA),
                                    Card.of(CardNumber.SIX, CardShape.DIA),
                                    Card.of(CardNumber.ACE, CardShape.DIA));
                }),
                DynamicTest.dynamicTest("딜러의 점수가 21이하면 카드를 더 받지 않고 Stand상태이다.", () -> {
                    // when
                    dealer.playTurn(deck);

                    // then
                    assertAll(
                            () -> assertThat(dealer.getHands().getCards())
                                    .containsExactly(Card.of(CardNumber.TEN, CardShape.DIA),
                                            Card.of(CardNumber.SIX, CardShape.DIA),
                                            Card.of(CardNumber.ACE, CardShape.DIA)),
                            () -> assertThat(dealer.getState()).isInstanceOf(StandState.class)
                    );

                })
        );
    }
}
