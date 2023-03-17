package blackjack.model.result;

import blackjack.model.HandFixtures;
import blackjack.model.participant.BetAmount;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Name;
import blackjack.model.participant.Player;
import blackjack.model.state.BlackjackState;
import blackjack.model.state.BustState;
import blackjack.model.state.StandState;
import blackjack.model.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ResultTest {

    @Nested
    @DisplayName("플레이어가 버스트일 때")
    class player_bust {
        private final State playerState = new BustState(HandFixtures.createPlayerBust());
        private final int betAmount = 10000;
        private final Player player = new Player(new Name("이리내"), new BetAmount(betAmount), playerState);

        @Nested
        @DisplayName("딜러가 버스트라면")
        class dealer_bust {
            private final Dealer dealer = new Dealer(new BustState(HandFixtures.createDealerBust()));

            @Test
            @DisplayName("플레이어가 패배한다")
            void player_lose() {
                Result result = Result.checkPlayerResult(player, dealer);
                assertThat(result).isEqualTo(Result.LOSE);
            }
        }

        @Nested
        @DisplayName("딜러가 블랙잭이라면")
        class dealer_blackjack {
            private final Dealer dealer = new Dealer(new BlackjackState(HandFixtures.createDealerBlackjack()));

            @Test
            @DisplayName("플레이어가 패배한다")
            void player_lose() {
                Result result = Result.checkPlayerResult(player, dealer);
                assertThat(result).isEqualTo(Result.LOSE);
            }
        }

        @Nested
        @DisplayName("딜러가 스탠드라면")
        class dealer_stand {
            private final Dealer dealer = new Dealer(new StandState(HandFixtures.createDealer19Score()));

            @Test
            @DisplayName("플레이어가 패배한다")
            void player_lose() {
                Result result = Result.checkPlayerResult(player, dealer);
                assertThat(result).isEqualTo(Result.LOSE);
            }
        }
    }

    @Nested
    @DisplayName("플레이어가 블랙잭일 때")
    class player_blackjack {
        private final State playerState = new BlackjackState(HandFixtures.createPlayerBlackjack());
        private final int betAmount = 10000;
        private final Player player = new Player(new Name("이리내"), new BetAmount(betAmount), playerState);

        @Nested
        @DisplayName("딜러가 버스트라면")
        class dealer_bust {
            private final Dealer dealer = new Dealer(new BustState(HandFixtures.createDealerBust()));

            @Test
            @DisplayName("플레이어가 승리한다")
            void player_win() {
                Result result = Result.checkPlayerResult(player, dealer);
                assertThat(result).isEqualTo(Result.WIN);
            }
        }

        @Nested
        @DisplayName("딜러가 블랙잭이라면")
        class dealer_blackjack {
            private final Dealer dealer = new Dealer(new BlackjackState(HandFixtures.createDealerBlackjack()));

            @Test
            @DisplayName("플레이어는 무승부다")
            void player_tie() {
                Result result = Result.checkPlayerResult(player, dealer);
                assertThat(result).isEqualTo(Result.TIE);
            }
        }

        @Nested
        @DisplayName("딜러가 스탠드라면")
        class dealer_stand {
            private final Dealer dealer = new Dealer(new StandState(HandFixtures.createDealer19Score()));

            @Test
            @DisplayName("플레이어가 승리한다")
            void player_win() {
                Result result = Result.checkPlayerResult(player, dealer);
                assertThat(result).isEqualTo(Result.WIN);
            }
        }
    }

    @Nested
    @DisplayName("플레이어가 스탠드일 때")
    class player_stand {
        private final State playerState = new StandState(HandFixtures.createPlayer19Score());
        private final int betAmount = 10000;
        private final Player player = new Player(new Name("이리내"), new BetAmount(betAmount), playerState);

        @Nested
        @DisplayName("딜러가 버스트라면")
        class dealer_bust {
            private final Dealer dealer = new Dealer(new BustState(HandFixtures.createDealerBust()));

            @Test
            @DisplayName("플레이어가 승리한다")
            void player_win() {
                Result result = Result.checkPlayerResult(player, dealer);
                assertThat(result).isEqualTo(Result.WIN);
            }
        }

        @Nested
        @DisplayName("딜러가 블랙잭이라면")
        class dealer_blackjack {
            private final Dealer dealer = new Dealer(new BlackjackState(HandFixtures.createDealerBust()));

            @Test
            @DisplayName("플레이어는 패배한다")
            void player_lose() {
                Result result = Result.checkPlayerResult(player, dealer);
                assertThat(result).isEqualTo(Result.LOSE);
            }
        }

        @Nested
        @DisplayName("딜러가 스탠드라면")
        class dealer_stand {

            @Test
            @DisplayName("플레이어의 총점이 딜러보다 높으면 플레이어가 승리한다.")
            void player_win() {
                //given
                Dealer dealer = new Dealer(new StandState(HandFixtures.createDealer17Score()));

                //when
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(result).isEqualTo(Result.WIN);
            }

            @Test
            @DisplayName("플레이어의 총점과 딜러의 총점이 같으면 무승부다.")
            void player_tie() {
                //given
                Dealer dealer = new Dealer(new StandState(HandFixtures.createDealer19Score()));

                //when
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(result).isEqualTo(Result.TIE);
            }

            @Test
            @DisplayName("플레이어의 총점이 딜러의 총점보다 작으면 패배한다.")
            void player_lose() {
                //given
                Dealer dealer = new Dealer(new StandState(HandFixtures.createDealer20Score()));

                //when
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(result).isEqualTo(Result.LOSE);
            }
        }
    }
}
