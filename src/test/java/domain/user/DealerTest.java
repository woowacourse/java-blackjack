package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CloverCard;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("딜러는 ")
class DealerTest {
    @Test
    @DisplayName("합산 점수가 21을 초과하면 BUST 상태가 된다.")
    void checkBustByScoreTest() {
        //given
        List<Card> firstTurnCards = List.of(CloverCard.CLOVER_TEN, CloverCard.CLOVER_KING);
        Dealer dealer = new Dealer(firstTurnCards);

        //when
        dealer.receiveCard(CloverCard.CLOVER_QUEEN);
        UserStatus result = dealer.getStatus();

        //then
        assertThat(result).isEqualTo(DealerStatus.BUST);
    }

    @Test
    @DisplayName("합산 점수가 17~21 사이 값을 가지면 NORMAL 상태가 된다.")
    void checkNormalByScoreTest() {
        //given
        List<Card> firstTurnCards = List.of(CloverCard.CLOVER_TEN, CloverCard.CLOVER_THREE);
        Dealer dealer = new Dealer(firstTurnCards);

        //when
        dealer.receiveCard(CloverCard.CLOVER_FOUR);
        UserStatus result = dealer.getStatus();

        //then
        assertThat(result).isEqualTo(DealerStatus.NORMAL);
    }

    @Test
    @DisplayName("합산 점수가 17 미만이면 UNDER_SEVENTEEN 상태가 된다.")
    void checkUnderSeventeenByScoreTest() {
        //given
        List<Card> firstTurnCards = List.of(CloverCard.CLOVER_TEN, CloverCard.CLOVER_TWO);
        Dealer dealer = new Dealer(firstTurnCards);

        //when
        dealer.receiveCard(CloverCard.CLOVER_THREE);
        UserStatus result = dealer.getStatus();

        //then
        assertThat(result).isEqualTo(DealerStatus.UNDER_SEVENTEEN);
    }
}
