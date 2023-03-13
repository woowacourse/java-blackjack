package model.user;

import static model.card.CardFixture.DIAMOND_ACE;
import static model.card.CardFixture.DIAMOND_FIVE;
import static model.card.CardFixture.DIAMOND_JACK;
import static model.card.CardFixture.DIAMOND_KING;
import static model.card.CardFixture.DIAMOND_SIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import model.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    private User user;

    @BeforeEach
    void init() {
        user = new User(new Name("bebe"));
    }

    @DisplayName("플레이어가 카드를 받는지 테스트한다")
    @Test
    void receiveCardTest() {
        // given
        Card spadeFive = DIAMOND_FIVE;
        Card cloverAce = DIAMOND_ACE;

        user.receiveCard(spadeFive);
        user.receiveCard(cloverAce);

        // when, then
        assertThat(user.getHand().getCards()).containsExactly(spadeFive, cloverAce);
    }

    @DisplayName("user의 카드 값의 총 합을 반환한다.")
    @Test
    void calculateTotalValue() {
        // given
        user.receiveCard(DIAMOND_FIVE);
        user.receiveCard(DIAMOND_ACE);

        // when, then
        assertAll(
                () -> assertThat(user.calculateTotalValue()).isEqualTo(16),
                () -> {
                    user.receiveCard(DIAMOND_JACK);
                    assertThat(user.calculateTotalValue()).isEqualTo(16);
                }
        );
    }

    @Test
    @DisplayName("블랙잭 여부를 확인한다.")
    void whenBlackJack_thenReturnTrue() {
        // given
        user.receiveCard(DIAMOND_ACE);
        user.receiveCard(DIAMOND_KING);

        // when, then
        assertAll(
                () -> assertThat(user.isBlackJack()).isTrue(),
                () -> {
                    user.receiveCard(DIAMOND_ACE);
                    assertThat(user.isBlackJack()).isFalse();
                }
        );
    }

    @Test
    @DisplayName("카드를 추가적으로 받았을 경우 블랙잭 여부를 확인한다.")
    void givenThreeCards_whenBlackJack_thenReturnTrue() {
        // given
        user.receiveCard(DIAMOND_FIVE);
        user.receiveCard(DIAMOND_SIX);
        user.receiveCard(DIAMOND_KING);

        // when, then
        assertThat(user.isBlackJack()).isFalse();
    }
}
