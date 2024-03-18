package blackjack.domain.handrank;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.fixture.HandFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class StandTest {

    private final HandRank STAND = new Stand(HandFixture.CARDS_SCORE_16);

    @DisplayName("딜러가 21점 이하 점수의 일반적인 경우")
    @Nested
    class MatchTest {
        @DisplayName("플레이어가 블랙잭인 경우, 플레이어가 블랙잭으로 승리한다.")
        @Test
        void whenOnlyPlayerBlackjack_playerBlackjack() {
            HandRank dealer = new Stand(HandFixture.CARDS_SCORE_21);
            HandRank player = new Blackjack(HandFixture.BLACKJACK);

            assertThat(dealer.matchWithPlayer(player)).isEqualTo(SingleMatchResult.PLAYER_BLACKJACK);
        }

        @DisplayName("플레이어가 버스트인 경우, 딜러가 승리한다.")
        @Test
        void whenOnlyPlayerBust_DealerWin() {
            HandRank dealer = STAND;
            HandRank player = new Bust(HandFixture.BUSTED);

            assertThat(dealer.matchWithPlayer(player)).isEqualTo(SingleMatchResult.DEALER_WIN);
        }

        @DisplayName("플레이어가 딜러보다 점수가 높을 경우, 플레이러가 승리한다.")
        @Test
        void whenPlayerScoreIsMoreThanDealerScore_PlayerWin() {
            HandRank dealer = new Stand(HandFixture.CARDS_SCORE_16);
            HandRank player = new Stand(HandFixture.CARDS_SCORE_17);

            assertThat(dealer.matchWithPlayer(player)).isEqualTo(SingleMatchResult.PLAYER_WIN);
        }

        @DisplayName("플레이어와 딜러의 점수가 같을 경우, 비긴다.")
        @Test
        void whenPlayerScoreIsEqualToDealerScore_Draw() {
            HandRank dealer = new Stand(HandFixture.CARDS_SCORE_16);
            HandRank player = new Stand(HandFixture.CARDS_SCORE_16);

            assertThat(dealer.matchWithPlayer(player)).isEqualTo(SingleMatchResult.DRAW);
        }

        @DisplayName("플레이어가 딜러보다 점수가 높을 경우, 플레이러가 승리한다.")
        @Test
        void whenPlayerScoreIsLessThanDealerScore_Dealer() {
            HandRank dealer = new Stand(HandFixture.CARDS_SCORE_17);
            HandRank player = new Stand(HandFixture.CARDS_SCORE_16);

            assertThat(dealer.matchWithPlayer(player)).isEqualTo(SingleMatchResult.DEALER_WIN);
        }
    }
}
