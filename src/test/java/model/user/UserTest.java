package model.user;

import model.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static model.card.Shape.CLOVER;
import static model.card.Shape.DIAMOND;
import static model.card.Shape.SPADE;
import static model.card.Value.ACE;
import static model.card.Value.FIVE;
import static model.card.Value.JACK;
import static model.card.Value.KING;
import static model.card.Value.NINE;
import static model.card.Value.THREE;
import static model.card.Value.TWO;
import static model.user.Score.LOSE;
import static model.user.Score.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UserTest {

    private User user;

    @BeforeEach
    void init() {
        user = new User("bebe");
    }

    @DisplayName("플레이어가 카드를 받는지 테스트한다")
    @Test
    void receiveCardTest() {
        // given
        Card spadeFive = new Card(SPADE, FIVE);
        Card cloverAce = new Card(CLOVER, ACE);

        user.receiveCard(spadeFive);
        user.receiveCard(cloverAce);

        // when, then
        assertThat(user.getHand().getCards()).containsExactly(spadeFive, cloverAce);
    }

    @DisplayName("user의 카드 값의 총 합을 반환한다.")
    @Test
    void calculateTotalValue() {
        // given
        Card spadeFive = new Card(SPADE, FIVE);
        Card cloverAce = new Card(CLOVER, ACE);

        user.receiveCard(spadeFive);
        user.receiveCard(cloverAce);

        // when, then
        assertAll(
                () -> assertThat(user.getCardTotalValue()).isEqualTo(16),
                () -> {
                    user.receiveCard(new Card(DIAMOND, JACK));
                    assertThat(user.getCardTotalValue()).isEqualTo(16);
                }
        );
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 21 초과일 경우 플레이어가 패배한 결과가 반환된다.")
    void whenOverBurstNumberFindWinPlayer() {
        // given
        int dealerTotalValue = 23;

        user.receiveCard(new Card(SPADE, JACK));
        user.receiveCard(new Card(DIAMOND, JACK));
        user.receiveCard(new Card(CLOVER, TWO));

        // when, then
        assertThat(user.judgeResult(dealerTotalValue)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("딜러가 21이하이고 플레이어는 21초과이면 플레이어의 패배가 반환된다.")
    void whenPlayerBurstReturnFalse() {
        // given
        int dealerTotalValue = 10;

        user.receiveCard(new Card(SPADE, JACK));
        user.receiveCard(new Card(DIAMOND, NINE));
        user.receiveCard(new Card(CLOVER, THREE));

        // when, then
        assertThat(user.judgeResult(dealerTotalValue)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("딜러가 21초과이고 플레이어는 21이하이면 플레이어의 승리가 반환된다.")
    void whenDealerBurstReturnTrue() {
        // given
        int dealerTotalValue = 22;

        user.receiveCard(new Card(SPADE, KING));

        // when, then
        assertThat(user.judgeResult(dealerTotalValue)).isEqualTo(WIN);
    }
}
