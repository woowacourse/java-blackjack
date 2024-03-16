package blackjack.domain.player;

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

class PlayerTest {

    @DisplayName("계속 Hit하는 플레이어의 턴 진행 시나리오")
    @TestFactory
    Collection<DynamicTest> playTurnHit() {
        // given
        final User player = new Player(new UserName("pobi"), name -> true);
        final Deck deck = new Deck(
                List.of(Card.of(CardNumber.TEN, CardShape.DIA),
                        Card.of(CardNumber.QUEEN, CardShape.DIA),
                        Card.of(CardNumber.ACE, CardShape.DIA),
                        Card.of(CardNumber.ACE, CardShape.SPADE)));

        return List.of(
                DynamicTest.dynamicTest("플레이어는 생성시 초기 상태이다.", () -> {
                    assertThat(player.getState().isInit()).isTrue();
                }),
                DynamicTest.dynamicTest("플레이어는 초기 상태에서 1장 받을 수 있고 초기상태다.", () -> {
                    // when
                    player.playTurn(deck);

                    // then
                    assertAll(
                            () -> assertThat(player.getHands().getCards())
                                    .containsExactly(Card.of(CardNumber.TEN, CardShape.DIA)),
                            () -> assertThat(player.getState().isInit()).isTrue()
                    );
                }),
                DynamicTest.dynamicTest("플레이어는 1장받은 상태에서 1장 더 받으면 초기상태가 아니다.", () -> {
                    // when
                    player.playTurn(deck);

                    // then
                    assertAll(
                            () -> assertThat(player.getHands().getCards())
                                    .containsExactly(Card.of(CardNumber.TEN, CardShape.DIA),
                                            Card.of(CardNumber.QUEEN, CardShape.DIA)),
                            () -> assertThat(player.getState().isInit()).isFalse()
                    );
                }),
                DynamicTest.dynamicTest("Hit상태의 플레이어는 카드를 더 받을 수 있다.", () -> {
                    // when
                    player.playTurn(deck);

                    // then
                    assertThat(player.getHands().getCards())
                            .containsExactly(Card.of(CardNumber.TEN, CardShape.DIA),
                                    Card.of(CardNumber.QUEEN, CardShape.DIA),
                                    Card.of(CardNumber.ACE, CardShape.DIA));
                }),
                DynamicTest.dynamicTest("플레이어가 카드를 더 받고 21을 초과하면 Bust상태이다.", () -> {
                    // when
                    player.playTurn(deck);

                    // then
                    assertAll(
                            () -> assertThat(player.getHands().getCards())
                                    .containsExactly(Card.of(CardNumber.TEN, CardShape.DIA),
                                            Card.of(CardNumber.QUEEN, CardShape.DIA),
                                            Card.of(CardNumber.ACE, CardShape.DIA),
                                            Card.of(CardNumber.ACE, CardShape.SPADE)),
                            () -> assertThat(player.getState().isBust()).isTrue()
                    );
                })
        );
    }

    @DisplayName("계속 Stand하는 플레이어의 턴 진행 시나리오")
    @TestFactory
    Collection<DynamicTest> playTurnStand() {
        // given
        final User player = new Player(new UserName("pobi"), name -> false);
        final Deck deck = new Deck(
                List.of(Card.of(CardNumber.TEN, CardShape.DIA),
                        Card.of(CardNumber.QUEEN, CardShape.DIA),
                        Card.of(CardNumber.ACE, CardShape.DIA),
                        Card.of(CardNumber.ACE, CardShape.SPADE)));

        return List.of(
                DynamicTest.dynamicTest("플레이어는 생성시 초기 상태이다.", () -> {
                    assertThat(player.getState().isInit()).isTrue();
                }),
                DynamicTest.dynamicTest("플레이어는 초기 상태에서 1장 받을 수 있고 초기상태다.", () -> {
                    // when
                    player.playTurn(deck);

                    // then
                    assertAll(
                            () -> assertThat(player.getHands().getCards())
                                    .containsExactly(Card.of(CardNumber.TEN, CardShape.DIA)),
                            () -> assertThat(player.getState().isInit()).isTrue()
                    );
                }),
                DynamicTest.dynamicTest("플레이어는 1장받은 상태에서 1장 더 받으면 초기상태가 아니다.", () -> {
                    // when
                    player.playTurn(deck);

                    // then
                    assertAll(
                            () -> assertThat(player.getHands().getCards())
                                    .containsExactly(Card.of(CardNumber.TEN, CardShape.DIA),
                                            Card.of(CardNumber.QUEEN, CardShape.DIA)),
                            () -> assertThat(player.getState().isInit()).isFalse()
                    );
                }),
                DynamicTest.dynamicTest("플레이어가 stand하면 카드를 더 받지 않고 Stand 상태이다.", () -> {
                    // when
                    player.playTurn(deck);

                    // then
                    assertAll(
                            () -> assertThat(player.getHands().getCards())
                                    .containsExactly(Card.of(CardNumber.TEN, CardShape.DIA),
                                            Card.of(CardNumber.QUEEN, CardShape.DIA)),
                            () -> assertThat(player.getState()).isInstanceOf(StandState.class)
                    );
                })
        );
    }

}
