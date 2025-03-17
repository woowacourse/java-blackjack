package blackjack.result;

import blackjack.card.Card;
import blackjack.card.CardFixture;
import blackjack.card.CardRank;
import blackjack.card.CardSuit;
import blackjack.participant.Dealer;
import blackjack.participant.GameParticipant;
import blackjack.participant.GameParticipantFixture;
import blackjack.participant.GameParticipants;
import blackjack.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameStatisticsTest {

    private static final int DEFAULT_BETTING_AMOUNT = 10000;

    private GameParticipants createGameParticipants(List<Player> players, Dealer dealer) {
        return GameParticipants.of(players, dealer);
    }

    private void drawCards(GameParticipant participant, int... cardValues) {
        for (int value : cardValues) {
            participant.drawCard(CardFixture.createCard(value));
        }
    }

    @Test
    @DisplayName("플레이어별 최종 수익을 계산할 수 있다.")
    void shouldCalculatePlayerProfitCorrectly() {
        // given
        Player player1 = GameParticipantFixture.createPlayer("강산", Betting.from(DEFAULT_BETTING_AMOUNT));
        Player player2 = GameParticipantFixture.createPlayer("재중", Betting.from(DEFAULT_BETTING_AMOUNT));
        Player player3 = GameParticipantFixture.createPlayer("아현", Betting.from(DEFAULT_BETTING_AMOUNT));
        Player player4 = GameParticipantFixture.createPlayer("카드3합21", Betting.from(DEFAULT_BETTING_AMOUNT));
        Player player5 = GameParticipantFixture.createPlayer("카드2합21", Betting.from(DEFAULT_BETTING_AMOUNT));

        Dealer dealer = GameParticipantFixture.createDealer();
        drawCards(dealer, 9, 9); // 딜러: 18점

        drawCards(player1, 10, 10);  // 20점

        drawCards(player2, 9, 9);    // 18점

        drawCards(player3, 8, 8);    // 16점

        drawCards(player4, 7, 7, 7); // 21점 (3장)

        drawCards(player5, 10);
        player5.drawCard(Card.of(CardSuit.SPADE, CardRank.ACE)); // 블랙잭 (2장 21점)


        GameStatistics statistics = GameStatistics.from(createGameParticipants(List.of(player1, player2, player3, player4, player5), dealer));

        int[] expectedPlayerProfits = {10000, 0, -10000, 10000, 5000};

        // when
        // then
        assertAll(() -> {
            List<Player> players = List.of(player1, player2, player3, player4, player5);
            for (int i = 0; i < players.size(); i++) {
                assertThat(statistics.getProfit(players.get(i))).isEqualTo(Money.from(expectedPlayerProfits[i]));
            }
        });
    }

    @Test
    @DisplayName("다양한 베팅 금액을 설정하여 최종 수익을 계산할 수 있다.")
    void shouldCalculateProfitsForVariousBettingAmounts() {
        // given
        Player player1 = GameParticipantFixture.createPlayer("강산", Betting.from(5000));
        Player player2 = GameParticipantFixture.createPlayer("재중", Betting.from(20000));
        Player player3 = GameParticipantFixture.createPlayer("아현", Betting.from(1000));
        Player player4 = GameParticipantFixture.createPlayer("카드3합21", Betting.from(25000));
        Player player5 = GameParticipantFixture.createPlayer("카드2합21", Betting.from(7500));

        Dealer dealer = GameParticipantFixture.createDealer();
        drawCards(dealer, 9, 9);

        drawCards(player1, 10, 10);

        drawCards(player2, 9, 9);

        drawCards(player3, 8, 8);

        drawCards(player4, 7, 7, 7);

        drawCards(player5, 10);
        player5.drawCard(Card.of(CardSuit.SPADE, CardRank.ACE));


        GameStatistics statistics = GameStatistics.from(createGameParticipants(List.of(player1, player2, player3, player4, player5), dealer));

        int[] expectedProfits = {5000, 0, -1000, 25000, 3750};

        // when 
        // then
        assertAll(() -> {
            List<Player> players = List.of(player1, player2, player3, player4, player5);
            for (int i = 0; i < players.size(); i++) {
                assertThat(statistics.getProfit(players.get(i))).isEqualTo(Money.from(expectedProfits[i]));
            }
        });
    }

    @Test
    @DisplayName("소숫점 절삭 검증: 1원의 25%는 0원이 되어야 한다.")
    void shouldTruncateDecimalCorrectly() {
        // given
        Money money = Money.from(1);

        // when
        Money result = money.applyProfitRate(25); // 1 * 25 / 100 = 0.25 → 0원

        // then
        assertThat(result).isEqualTo(Money.from(0));
    }

    @Test
    @DisplayName("모든 플레이어와 딜러의 최종 수익 합이 0이 되는 케이스 검증")
    void shouldEnsureTotalProfitSumIsZero() {
        // given
        Player player1 = GameParticipantFixture.createPlayer("강산", Betting.from(10000));
        Player player2 = GameParticipantFixture.createPlayer("재중", Betting.from(5000));
        Dealer dealer = GameParticipantFixture.createDealer();

        drawCards(player1, 10, 10);  // 20점
        drawCards(player2, 9, 9);    // 18점
        drawCards(dealer, 9, 9); // 딜러: 18점

        GameStatistics statistics = GameStatistics.from(createGameParticipants(List.of(player1, player2), dealer));

        Money totalPlayerProfit = Money.sumOf(List.of(
                statistics.getProfit(player1),
                statistics.getProfit(player2)
        ));

        Money dealerProfit = statistics.getDealerProfit();

        // when
        Money totalProfit = totalPlayerProfit.plus(dealerProfit);

        // then
        assertThat(totalProfit.getAmount()).isEqualTo(0);
    }

    @Test
    @DisplayName("모든 플레이어와 딜러의 최종 수익 합이 0이 되지 않는 케이스 검증 (절삭 고려)")
    void shouldEnsureTotalProfitSumIsCloseToZero() {
        // given
        Player player1 = GameParticipantFixture.createPlayer("강산", Betting.from(10000));
        Player player2 = GameParticipantFixture.createPlayer("재중", Betting.from(3333));
        Dealer dealer = GameParticipantFixture.createDealer();

        drawCards(player1, 10, 10);  // 20점
        drawCards(player2, 9, 9);    // 18점
        drawCards(dealer, 9, 9); // 딜러: 18점

        GameStatistics statistics = GameStatistics.from(createGameParticipants(List.of(player1, player2), dealer));

        Money totalPlayerProfit = Money.sumOf(List.of(
                statistics.getProfit(player1),
                statistics.getProfit(player2)
        ));

        Money dealerProfit = statistics.getDealerProfit();

        // when
        Money totalProfit = totalPlayerProfit.plus(dealerProfit);

        // then
        assertThat(Math.abs(totalProfit.getAmount())).isLessThanOrEqualTo(1); // ✅ 절삭을 고려한 오차 허용
    }
}
