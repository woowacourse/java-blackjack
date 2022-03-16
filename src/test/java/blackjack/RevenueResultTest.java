package blackjack;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.FOUR;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.utils.ParticipantsCreationUtils.createDealerWithDenominations;
import static blackjack.utils.ParticipantsCreationUtils.createPlayerWithDenominations;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.BettingMoney;
import blackjack.domain.ScoreBoard;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RevenueResultTest {

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러는 아닐 때 플레이어의 수익률은 1.5 배이다")
    void player_blackjack_when_player_blackjack_and_dealer_is_not() {
        // given
        Player player = createPlayerWithDenominations("user a", ACE, JACK);
        Dealer dealer = createDealerWithDenominations(TEN, QUEEN);

        List<BettingMoney> bettingMonies = new ArrayList<>();
        bettingMonies.add(new BettingMoney(player.getName(), "10000"));

        // when
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
        RevenueResult revenueResult = RevenueResult.of(scoreBoard, bettingMonies);

        // then
        assertThat(revenueResult.findPlayerEarning(player.getName())).isEqualTo(15_000);
        assertThat(revenueResult.getDealerEarnings()).isEqualTo(-15_000);
    }

    @Test
    @DisplayName("플레이어와 플레이어 모두 블랙잭일 때 무승부이기에 플레이어의 수익률은 0 이다")
    void player_draw_when_player_blackjack_and_dealer_is_not() {
        // given
        Player player = createPlayerWithDenominations("user a", ACE, JACK);
        Dealer dealer = createDealerWithDenominations(ACE, TEN);

        List<BettingMoney> bettingMonies = new ArrayList<>();
        bettingMonies.add(new BettingMoney(player.getName(), "10000"));

        // when
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
        RevenueResult revenueResult = RevenueResult.of(scoreBoard, bettingMonies);

        // then
        assertThat(revenueResult.findPlayerEarning(player.getName())).isEqualTo(0);
        assertThat(revenueResult.getDealerEarnings()).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어가 딜러를 이겼을 때 플레이어의 수익률은 1 이다")
    void player_win() {
        // given
        Player player = createPlayerWithDenominations("user a", NINE, TEN);
        Dealer dealer = createDealerWithDenominations(FIVE, EIGHT);

        List<BettingMoney> bettingMonies = new ArrayList<>();
        bettingMonies.add(new BettingMoney(player.getName(), "10000"));

        // when
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
        RevenueResult revenueResult = RevenueResult.of(scoreBoard, bettingMonies);

        // then
        assertThat(revenueResult.findPlayerEarning(player.getName())).isEqualTo(10_000);
        assertThat(revenueResult.getDealerEarnings()).isEqualTo(-10_000);
    }

    @Test
    @DisplayName("플레이어가 딜러에게 졌을 때 플레이어의 수익률은 -1 이다")
    void player_lose() {
        // given
        Player player = createPlayerWithDenominations("user a", FIVE, EIGHT);
        Dealer dealer = createDealerWithDenominations(NINE, TEN);

        List<BettingMoney> bettingMonies = new ArrayList<>();
        bettingMonies.add(new BettingMoney(player.getName(), "10000"));

        // when
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
        RevenueResult revenueResult = RevenueResult.of(scoreBoard, bettingMonies);

        // then
        assertThat(revenueResult.findPlayerEarning(player.getName())).isEqualTo(-10_000);
        assertThat(revenueResult.getDealerEarnings()).isEqualTo(10_000);
    }

    @Test
    @DisplayName("플레이어가 딜러와 비겼을 때 플레이어의 수익률은 0 이다")
    void player_draw() {
        // given
        Player player = createPlayerWithDenominations("user a", FOUR, EIGHT);
        Dealer dealer = createDealerWithDenominations(THREE, NINE);

        List<BettingMoney> bettingMonies = new ArrayList<>();
        bettingMonies.add(new BettingMoney(player.getName(), "10000"));

        // when
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
        RevenueResult revenueResult = RevenueResult.of(scoreBoard, bettingMonies);

        // then
        assertThat(revenueResult.findPlayerEarning(player.getName())).isEqualTo(0);
        assertThat(revenueResult.getDealerEarnings()).isEqualTo(0);
    }

    @Nested
    @DisplayName("딜러가 버스트일 때")
    static class DealerIsBust {

        private Dealer dealer = createDealerWithDenominations(TEN, NINE, THREE);

        @Test
        @DisplayName("플레이어가 블랙잭 플레이어의 수익률은 1.5이다")
        void player_21_then_1() {
        	// given
            List<BettingMoney> bettingMonies = new ArrayList<>();
            Player player = createPlayerWithDenominations("user a", ACE, JACK);
            bettingMonies.add(new BettingMoney(player.getName(), "10000"));

        	// when
            ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
            RevenueResult revenueResult = RevenueResult.of(scoreBoard, bettingMonies);

        	// then
            assertThat(revenueResult.findPlayerEarning(player.getName())).isEqualTo(15_000);
            assertThat(revenueResult.getDealerEarnings()).isEqualTo(-15_000);
        }

        @Test
        @DisplayName("플레이어의 점수가 20이면 플레이어의 수익률은 1이다")
        void player_20_then_1() {
            // given
            List<BettingMoney> bettingMonies = new ArrayList<>();
            Player player = createPlayerWithDenominations("user a", JACK, EIGHT, TWO);
            bettingMonies.add(new BettingMoney(player.getName(), "10000"));

            // when
            ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
            RevenueResult revenueResult = RevenueResult.of(scoreBoard, bettingMonies);

            // then
            assertThat(revenueResult.findPlayerEarning(player.getName())).isEqualTo(10_000);
            assertThat(revenueResult.getDealerEarnings()).isEqualTo(-10_000);
        }

        @Test
        @DisplayName("플레이어 또한 버스트라면 플레이어의 수익률은 -1이다")
        void also_player_bust_then_1() {
            // given
            List<BettingMoney> bettingMonies = new ArrayList<>();
            Player player = createPlayerWithDenominations("user a", JACK, QUEEN, TWO);
            bettingMonies.add(new BettingMoney(player.getName(), "10000"));

            // when
            ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
            RevenueResult revenueResult = RevenueResult.of(scoreBoard, bettingMonies);

            // then
            assertThat(revenueResult.findPlayerEarning(player.getName())).isEqualTo(-10_000);
            assertThat(revenueResult.getDealerEarnings()).isEqualTo(10_000);
        }
    }

}
