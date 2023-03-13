package model.user;

import static model.card.CardFixture.DIAMOND_ACE;
import static model.card.CardFixture.DIAMOND_FIVE;
import static model.card.CardFixture.DIAMOND_KING;
import static model.card.CardFixture.DIAMOND_NINE;
import static model.card.CardFixture.DIAMOND_SEVEN;
import static model.card.CardFixture.DIAMOND_SIX;
import static model.card.CardFixture.DIAMOND_TEN;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import model.card.Card;
import model.card.Deck;
import model.card.RandomShuffleMaker;
import model.money.Bet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    private Player player;
    private Dealer dealer;

    @BeforeEach
    void init() {
        player = new Player("ethan", new Bet(10_000));
        dealer = new Dealer();
    }

    @DisplayName("플레이어가 카드를 받는지 테스트한다")
    @Test
    void receiveCardTest() {
        // given
        Card spadeFive = DIAMOND_FIVE;
        Card cloverAce = DIAMOND_ACE;

        player.receiveCard(spadeFive);
        player.receiveCard(cloverAce);

        // when, then
        assertThat(player.getHand().getCards()).containsExactly(spadeFive, cloverAce);
    }

    @ParameterizedTest
    @MethodSource("initialCard")
    @DisplayName("플레이어의 카드 총 합이 21 이하인지 판단한다.")
    void canReceiveCardTest(final Card first, final Card second, final Card third, final Boolean result) {
        // given
        player.receiveCard(first);
        player.receiveCard(second);
        player.receiveCard(third);

        // when, then
        assertThat(player.canReceiveCard()).isEqualTo(result);
    }

    private static Stream<Arguments> initialCard() {
        return Stream.of(
                Arguments.of(DIAMOND_TEN, DIAMOND_SIX, DIAMOND_FIVE, Boolean.TRUE),
                Arguments.of(DIAMOND_TEN, DIAMOND_SEVEN, DIAMOND_FIVE, Boolean.FALSE)
        );
    }

    @DisplayName("receiveInitialCards가 두 장의 카드를 주는지 확인한다.")
    @Test
    void receiveInitialCards() {
        // given
        player.receiveInitialCards(Deck.create(new RandomShuffleMaker()));

        // when, then
        assertThat(player.getHand().getCards()).hasSize(2);
    }

    @Nested
    @DisplayName("딜러를 가지고 플레이어의 결과를 반환한다.")
    class JudgeResultWithDealer {

        @Test
        @DisplayName("플레이어가 블랙잭이면 BLACKJACK을 반환한다.")
        void judgeResultBlackJack() {
            // given
            player.receiveCard(DIAMOND_KING);
            player.receiveCard(DIAMOND_ACE);

            dealer.receiveCard(DIAMOND_SIX);
            dealer.receiveCard(DIAMOND_TEN);

            // when, then
            assertThat(player.judgeResult(dealer)).isEqualTo(GameState.BLACKJACK);
        }

        @Test
        @DisplayName("플레이어가 이기면 WIN을 반환한다.")
        void judgeResultWin() {
            // given
            player.receiveCard(DIAMOND_KING);
            player.receiveCard(DIAMOND_SEVEN);

            dealer.receiveCard(DIAMOND_SIX);
            dealer.receiveCard(DIAMOND_TEN);

            // when, then
            assertThat(player.judgeResult(dealer)).isEqualTo(GameState.WIN);
        }

        @Test
        @DisplayName("플레이어가 비기면 TIE를 반환한다.")
        void judgeResultTie() {
            // given
            player.receiveCard(DIAMOND_NINE);
            player.receiveCard(DIAMOND_SEVEN);

            dealer.receiveCard(DIAMOND_SIX);
            dealer.receiveCard(DIAMOND_TEN);

            // when, then
            assertThat(player.judgeResult(dealer)).isEqualTo(GameState.TIE);
        }

        @Test
        @DisplayName("플레이어가 지면 LOSE을 반환한다.")
        void judgeResultDraw() {
            // given
            player.receiveCard(DIAMOND_SIX);
            player.receiveCard(DIAMOND_TEN);

            dealer.receiveCard(DIAMOND_SEVEN);
            dealer.receiveCard(DIAMOND_TEN);

            // when, then
            assertThat(player.judgeResult(dealer)).isEqualTo(GameState.LOSE);
        }
    }
}
