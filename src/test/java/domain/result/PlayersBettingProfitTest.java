package domain.result;

import domain.card.CardRank;
import domain.card.CardSuit;
import domain.pariticipant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_util.TestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static domain.card.CardRank.*;
import static domain.card.CardSuit.*;
import static test_util.TestUtil.createCard;
import static test_util.TestUtil.createPlayer;


class PlayersBettingProfitTest {

    @Test
    @DisplayName("딜러의 수익을 계산한다.")
    public void calculateDealerProfit_success() throws Exception {
        // given
        Map<Player, Long> playersBettingProfitMap = Map.of(
                createPlayer("pobi",
                        List.of(createCard(HEART, TWO),
                                createCard(SPADE, EIGHT),
                                createCard(CLUB, ACE)), 10000), 10000L,
                createPlayer("jason",
                        List.of(createCard(CLUB, SEVEN),
                                createCard(SPADE, KING)), 20000), -20000L
        );
        PlayersBettingProfit playersBettingProfit = new PlayersBettingProfit(playersBettingProfitMap);

        // when
        long dealerProfit = playersBettingProfit.calculateDealerProfit();

        // then
        Assertions.assertThat(dealerProfit).isEqualTo(10000);
    }

}
