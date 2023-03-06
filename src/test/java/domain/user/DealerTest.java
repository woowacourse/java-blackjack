package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CloverCard;
import domain.card.HeartCard;
import domain.card.SpadeCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("딜러는 ")
class DealerTest {
    @DisplayName("합산 점수가 21을 초과하면 BUST 상태가 된다.")
    @Test
    void checkBustByScoreTest() {
        Dealer dealer = new Dealer();

        dealer.receiveCard(CloverCard.CLOVER_QUEEN);
        dealer.receiveCard(SpadeCard.SPADE_KING);
        dealer.receiveCard(HeartCard.HEART_JACK);
        UserStatus result = dealer.getStatus();

        assertThat(result).isEqualTo(DealerStatus.BUST);
    }

    @DisplayName("합산 점수가 17~21 사이 값을 가지면 NORMAL 상태가 된다.")
    @Test
    void checkNormalByScoreTest() {
        Dealer dealer = new Dealer();

        dealer.receiveCard(CloverCard.CLOVER_TEN);
        dealer.receiveCard(CloverCard.CLOVER_THREE);
        dealer.receiveCard(CloverCard.CLOVER_FOUR);
        UserStatus result = dealer.getStatus();

        assertThat(result).isEqualTo(DealerStatus.NORMAL);
    }

    @DisplayName("합산 점수가 17 미만이면 UNDER_SEVENTEEN 상태가 된다.")
    @Test
    void checkUnderSeventeenByScoreTest() {
        Dealer dealer = new Dealer();

        dealer.receiveCard(CloverCard.CLOVER_TEN);
        dealer.receiveCard(CloverCard.CLOVER_TWO);
        dealer.receiveCard(CloverCard.CLOVER_THREE);
        UserStatus result = dealer.getStatus();

        assertThat(result).isEqualTo(DealerStatus.UNDER_MIN_SCORE);
    }
}
