package blackjackgame.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjackgame.domain.card.CloverCard;
import blackjackgame.domain.card.HeartCard;
import blackjackgame.domain.card.SpadeCard;
import java.util.List;
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

    @DisplayName("합산 점수가 17 미만이면 UNDER_MIN_SCORE 상태가 된다.")
    @Test
        void checkUnderMinScoreByScoreTest() {
        Dealer dealer = new Dealer();

        dealer.receiveCard(CloverCard.CLOVER_TEN);
        dealer.receiveCard(CloverCard.CLOVER_TWO);
        dealer.receiveCard(CloverCard.CLOVER_THREE);
        UserStatus result = dealer.getStatus();

        assertThat(result).isEqualTo(DealerStatus.UNDER_MIN_SCORE);
    }

    @DisplayName("처음으로 받은 카드 외에 추가로 뽑은 카드가 몇 장인지 반환한다.")
    @Test
    void getExtraDrawCount_drawTwoMoreCards() {
        Dealer dealer = new Dealer();
        dealer.receiveCards(List.of(CloverCard.CLOVER_TWO, SpadeCard.SPADE_TWO));

        dealer.receiveCards(List.of(CloverCard.CLOVER_FIVE, SpadeCard.SPADE_TEN));
        int extraDrawCount = dealer.getExtraDrawCount(2);

        assertThat(extraDrawCount).isEqualTo(2);
    }
}
