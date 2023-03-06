package model;

import model.card.Card;
import model.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static model.card.Shape.CLOVER;
import static model.card.Shape.DIAMOND;
import static model.card.Shape.SPADE;
import static model.card.Value.ACE;
import static model.card.Value.FIVE;
import static model.card.Value.JACK;
import static model.card.Value.KING;
import static model.card.Value.NINE;
import static model.card.Value.SEVEN;
import static model.card.Value.SIX;
import static model.card.Value.TEN;
import static model.card.Value.THREE;
import static model.card.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private Player player;

    @BeforeEach
    void init() {
        player = new Player("ethan");
    }

    @DisplayName("플레이어가 카드를 받는지 테스트한다")
    @Test
    void receiveCardTest() {
        // given
        Card spadeFive = new Card(SPADE, FIVE);
        Card cloverAce = new Card(CLOVER, ACE);

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
                Arguments.of(new Card(DIAMOND, TEN), new Card(DIAMOND, SIX), new Card(DIAMOND, FIVE), Boolean.TRUE),
                Arguments.of(new Card(DIAMOND, TEN), new Card(DIAMOND, SEVEN), new Card(DIAMOND, FIVE), Boolean.FALSE)
        );
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 21 초과일 경우 플레이어가 이긴 결과가 반환된다.")
    void whenOverBurstNumberFindWinPlayer() {
        // given
        int dealerTotalValue = 23;

        player.receiveCard(new Card(SPADE, JACK));
        player.receiveCard(new Card(DIAMOND, JACK));
        player.receiveCard(new Card(CLOVER, TWO));

        // when, then
        assertThat(player.judgeResult(dealerTotalValue)).isTrue();
    }

    @Test
    @DisplayName("딜러가 21이하이고 플레이어는 21초과이면 플레이어의 패배가 반환된다.")
    void whenPlayerBurstReturnFalse() {
        // given
        int dealerTotalValue = 10;

        player.receiveCard(new Card(SPADE, JACK));
        player.receiveCard(new Card(DIAMOND, NINE));
        player.receiveCard(new Card(CLOVER, THREE));

        // when, then
        assertThat(player.judgeResult(dealerTotalValue)).isFalse();
    }

    @Test
    @DisplayName("딜러가 21초과이고 플레이어는 21이하이면 플레이어의 승리가 반환된다.")
    void whenDealerBurstReturnTrue() {
        // given
        int dealerTotalValue = 22;

        player.receiveCard(new Card(SPADE, KING));

        // when, then
        assertThat(player.judgeResult(dealerTotalValue)).isTrue();
    }
}
