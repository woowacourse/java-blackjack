package blackjack.result;

import blackjack.card.Card;
import blackjack.card.CardFixture;
import blackjack.card.CardRank;
import blackjack.card.CardSuit;
import blackjack.participant.Dealer;
import blackjack.participant.GameParticipantFixture;
import blackjack.participant.GameParticipants;
import blackjack.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameStatisticsTest {

    Player player1; // 20점 (승)
    Player player2; // 18점 (무)
    Player player3; // 16점 (패)
    Player player4; // 카드3 합21 (승)
    Player player5; // 카드2 합21 (승)
    Dealer dealer; // 18점
    GameParticipants participants;
    GameStatistics statistics;

    @BeforeEach
    void setUp() {
        int amount = 10000;

        player1 = GameParticipantFixture.createPlayer("강산", Betting.from(amount));
        player2 = GameParticipantFixture.createPlayer("재중", Betting.from(amount));
        player3 = GameParticipantFixture.createPlayer("아현", Betting.from(amount));
        player4 = GameParticipantFixture.createPlayer("카드3합21", Betting.from(amount));
        player5 = GameParticipantFixture.createPlayer("카드2합21", Betting.from(amount));
        dealer = GameParticipantFixture.createDealer();

        participants = GameParticipants.of(List.of(player1, player2, player3, player4, player5), dealer);
        player1.drawCard(CardFixture.createCard(10));
        player1.drawCard(CardFixture.createCard(10));

        player2.drawCard(CardFixture.createCard(9));
        player2.drawCard(CardFixture.createCard(9));

        player3.drawCard(CardFixture.createCard(8));
        player3.drawCard(CardFixture.createCard(8));

        player4.drawCard(CardFixture.createCard(7));
        player4.drawCard(CardFixture.createCard(7));
        player4.drawCard(CardFixture.createCard(7));

        player5.drawCard(CardFixture.createCard(10));
        player5.drawCard(Card.of(CardSuit.SPADE, CardRank.ACE));

        dealer.drawCard(CardFixture.createCard(9));
        dealer.drawCard(CardFixture.createCard(9));

        statistics = GameStatistics.from(participants);
    }

    @Test
    @DisplayName("모든 참여자의 최종 상금(원금이 제외되지 않은)을 가져올 수 있다.")
    void canGetPlayerPrize() {
        // given
        int expectedAmount1 = 10000; // 승리
        int expectedAmount2 = 0; // 무승부
        int expectedAmount3 = -10000; // 패배
        int expectedAmount4 = 10000; // 3장 21
        int expectedAmount5 = 5000; // 2장 21 (블랙잭)

        int expectedDealerAmount =
                expectedAmount1 + expectedAmount2 + expectedAmount3 + expectedAmount4 + expectedAmount5;

        // when
        // then
        assertAll(() ->
        {
            assertThat(statistics.getProfit(player1)).isEqualTo(Money.from(expectedAmount1));
            assertThat(statistics.getProfit(player2)).isEqualTo(Money.from(expectedAmount2));
            assertThat(statistics.getProfit(player3)).isEqualTo(Money.from(expectedAmount3));
            assertThat(statistics.getProfit(player4)).isEqualTo(Money.from(expectedAmount4));
            assertThat(statistics.getProfit(player5)).isEqualTo(Money.from(expectedAmount5));

            assertThat(statistics.getDealerProfit()).isEqualTo(Money.from(expectedDealerAmount));
        });
    }
}
