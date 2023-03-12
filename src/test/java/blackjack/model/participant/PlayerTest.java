package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.result.Result;
import blackjack.model.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static blackjack.model.Fixtures.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class PlayerTest {

    @Nested
    @DisplayName("플레이어가 카드를 가져가는 play 메소드 테스트")
    class play {
        @Test
        @DisplayName("게임을 시작하면 플레이어는 두 장씩의 카드를 지급받는다.")
        void player_initial_state_draw() {
            //given
            Player player = new Player(new Name("도치"), new BetAmount(10000), new PlayerInitialState(new Hand()));
            Card card1 = CLUB_EIGHT;
            Card card2 = CLUB_JACK;
            List<Card> cards = List.of(card1, card2);
            CardDeck cardDeck = new CardDeck(cards);

            // when
            player.play(cardDeck);

            //then
            assertThat(player.getHand()).containsExactly(card2, card1);
        }

        @Test
        @DisplayName("draw 상태의 플레이어는 버스트가 될 때까지 카드를 뽑을 수 있다.")
        void player_can_draw_until_bust() {
            //given
            List<Card> cards = List.of(HEART_EIGHT, HEART_NINE);
            Player player = new Player(new Name("도치"), new BetAmount(10000), new PlayerDrawState(new Hand(new ArrayList<>(List.of(CLUB_EIGHT, HEART_FIVE)))));
            CardDeck cardDeck = new CardDeck(cards);

            // when
            player.play(cardDeck);

            //then
            assertThatThrownBy(() -> player.play(cardDeck))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("카드를 더 뽑을 수 없습니다.");
        }

        @Test
        @DisplayName("blackjack 상태의 플레이어는 카드를 뽑을 수 없다.")
        void player_when_blackjack_can_not_draw() {
            Player player = new Player(new Name("도치"), new BetAmount(10000), new BlackjackState(new Hand(new ArrayList<>(List.of(HEART_JACK, HEART_ACE)))));
            CardDeck cardDeck = new CardDeck(List.of(CLUB_JACK));
            assertThatThrownBy(() -> player.play(cardDeck))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("카드를 더 뽑을 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("플레이어가 스탠드 상태로 전환하는 changeToStand 메소드 테스트")
    class changeToStand {
        @Test
        @DisplayName("플레이어는 점수가 21 이하일 경우 스탠드할 수 있다.")
        void player_can_stand() {
            //given
            State drawState = new PlayerDrawState(new Hand(List.of(CLUB_FIVE, CLUB_JACK)));
            Player player = new Player(new Name("이리내"), new BetAmount(10000), drawState);

            //when
            player.changeToStand();

            //then
            assertThat(player.isStand()).isTrue();
        }
    }

    @Nested
    @DisplayName("플레이어의 수익을 계산하는 getProfit 메소드 테스트")
    class getProfit {
        private final int betAmount = 10000;

        @Nested
        @DisplayName("플레이어가 게임이 끝나지 않았을 때")
        class player_playing {

            @Test
            @DisplayName("초기 카드를 배분받지 못한 상태에서 수익 계산을 시도하면 예외처리한다.")
            void player_cannot_calculate_money_when_initial() {
                State notFinishedState = new PlayerInitialState(new Hand());
                Player player = new Player(new Name("이리내"), new BetAmount(betAmount), notFinishedState);
                assertThatThrownBy(() -> player.getProfit(Result.WIN))
                        .isInstanceOf(IllegalStateException.class)
                        .hasMessage("게임이 끝나지 않아 수익을 알 수 없습니다.");
            }

            @Test
            @DisplayName("카드를 추가로 받아야 하는 상태에서 수익 계산을 시도하면 예외처리한다.")
            void player_cannot_calculate_money_when_draw() {
                State notFinishedState = new PlayerDrawState(new Hand(List.of(HEART_NINE, CLUB_NINE)));
                Player player = new Player(new Name("이리내"), new BetAmount(betAmount), notFinishedState);
                assertThatThrownBy(() -> player.getProfit(Result.WIN))
                        .isInstanceOf(IllegalStateException.class)
                        .hasMessage("게임이 끝나지 않아 수익을 알 수 없습니다.");
            }
        }

        @Nested
        @DisplayName("플레이어가 버스트일 때")
        class player_bust {
            private final State playerState = new BustState(new Hand(List.of(HEART_JACK, HEART_TEN, HEART_FIVE)));
            private final Player player = new Player(new Name("이리내"), new BetAmount(betAmount), playerState);

            @Test
            @DisplayName("딜러가 버스트라면 플레이어는 베팅 금액을 잃는다")
            void player_lost_x1_when_dealer_bust() {
                //given
                Dealer dealer = new Dealer(new BustState(new Hand(List.of(CLUB_JACK, CLUB_THREE, CLUB_NINE))));

                // when
                Result result = Result.checkPlayerResult(player, dealer);
                int playerProfit = player.getProfit(result);

                //then
                assertThat(player.isBust()).isTrue();
                assertThat(dealer.isBust()).isTrue();
                assertThat(playerProfit).isEqualTo(-1 * betAmount);
            }

            @Test
            @DisplayName("딜러가 스탠드라면 플레이어는 베팅 금액을 잃는다")
            void player_lost_x1_when_dealer_stand() {
                //given
                Dealer dealer = new Dealer(new StandState(new Hand(List.of(CLUB_JACK, CLUB_NINE))));

                // when
                Result result = Result.checkPlayerResult(player, dealer);
                int playerProfit = player.getProfit(result);

                //then
                assertThat(player.isBust()).isTrue();
                assertThat(dealer.isStand()).isTrue();
                assertThat(playerProfit).isEqualTo(-1 * betAmount);
            }

            @Test
            @DisplayName("딜러가 블랙잭이라면 플레이어는 베팅 금액을 잃는다")
            void player_lost_x1_when_dealer_blackjack() {
                //given
                Dealer dealer = new Dealer(new BlackjackState(new Hand(List.of(CLUB_ACE, CLUB_JACK))));

                // when
                Result result = Result.checkPlayerResult(player, dealer);
                int playerProfit = player.getProfit(result);

                //then
                assertThat(player.isBust()).isTrue();
                assertThat(dealer.isBlackjack()).isTrue();
                assertThat(playerProfit).isEqualTo(-1 * betAmount);
            }
        }

        @Nested
        @DisplayName("플레이어가 블랙잭일 때")
        class player_blackjack {
            private final State playerState = new BlackjackState(new Hand(List.of(HEART_JACK, HEART_ACE)));
            private final Player player = new Player(new Name("이리내"), new BetAmount(betAmount), playerState);

            @Test
            @DisplayName("딜러가 버스트라면 플레이어는 베팅 금액의 1.5배를 얻는다")
            void player_get_x1_5_when_dealer_bust() {
                //given
                Dealer dealer = new Dealer(new BustState(new Hand(List.of(CLUB_NINE, CLUB_FIVE, CLUB_JACK))));

                // when
                Result result = Result.checkPlayerResult(player, dealer);
                int playerProfit = player.getProfit(result);

                //then
                assertThat(playerProfit).isEqualTo((int)(betAmount * 1.5));
            }

            @Test
            @DisplayName("딜러가 스탠드라면 플레이어는 베팅 금액의 1.5배를 얻는다")
            void player_get_x1_5_when_dealer_stand() {
                //given
                Dealer dealer = new Dealer(new StandState(new Hand(List.of(CLUB_EIGHT, CLUB_NINE))));

                // when
                Result result = Result.checkPlayerResult(player, dealer);
                int playerProfit = player.getProfit(result);

                //then
                assertThat(playerProfit).isEqualTo((int)(betAmount * 1.5));
            }

            @Test
            @DisplayName("딜러가 블랙잭이라면 플레이어는 베팅 금액을 돌려받는다")
            void player_get_nothing_when_dealer_bust() {
                //given
                Dealer dealer = new Dealer(new BlackjackState(new Hand(List.of(CLUB_ACE, CLUB_JACK))));

                // when
                Result result = Result.checkPlayerResult(player, dealer);
                int playerProfit = player.getProfit(result);

                //then
                assertThat(playerProfit).isEqualTo(0);
            }
        }

        @Nested
        @DisplayName("플레이어가 스탠드일 때")
        class player_stand {
            private final State playerState = new StandState(new Hand(List.of(HEART_NINE, HEART_TEN)));
            private final Player player = new Player(new Name("이리내"), new BetAmount(betAmount), playerState);

            @Test
            @DisplayName("딜러가 버스트라면 플레이어는 베팅 금액의 1배를 얻는다")
            void player_get_x1_when_dealer_bust() {
                //given
                Dealer dealer = new Dealer(new BustState(new Hand(List.of(CLUB_NINE, CLUB_FIVE, CLUB_JACK))));

                // when
                Result result = Result.checkPlayerResult(player, dealer);
                int playerProfit = player.getProfit(result);

                //then
                assertThat(playerProfit).isEqualTo(betAmount);
            }

            @Test
            @DisplayName("딜러가 블랙잭이라면 플레이어는 베팅 금액을 잃는다")
            void player_lost_x1_when_dealer_blackjack() {
                //given
                Dealer dealer = new Dealer(new BlackjackState(new Hand(List.of(CLUB_ACE, CLUB_JACK))));

                // when

                Result result = Result.checkPlayerResult(player, dealer);
                int playerProfit = player.getProfit(result);

                //then
                assertThat(playerProfit).isEqualTo(-1 * betAmount);
            }

            @Test
            @DisplayName("딜러가 스탠드이고, 플레이어가 점수가 낮다면 플레이어는 베팅 금액을 잃는다")
            void player_lost_x1_when_dealer_stand_and_player_score_low() {
                //given
                Dealer dealer = new Dealer(new StandState(new Hand(List.of(CLUB_JACK, CLUB_TEN))));

                // when
                Result result = Result.checkPlayerResult(player, dealer);
                int playerProfit = player.getProfit(result);

                //then
                assertThat(playerProfit).isEqualTo(-1 * betAmount);
            }

            @Test
            @DisplayName("딜러가 스탠드이고, 플레이어가 점수가 높다면 플레이어는 베팅 금액의 x1배를 얻는다")
            void player_get_x1_when_dealer_stand_and_player_score_high() {
                //given
                Dealer dealer = new Dealer(new StandState(new Hand(List.of(CLUB_EIGHT, CLUB_NINE))));

                // when
                Result result = Result.checkPlayerResult(player, dealer);
                int playerProfit = player.getProfit(result);

                //then
                assertThat(playerProfit).isEqualTo(betAmount);
            }

            @Test
            @DisplayName("딜러가 스탠드이고, 플레이어와 딜러의 점수가 같다면 플레이어는 베팅 금액을 돌려받는다")
            void player_get_nothing_when_dealer_stand_and_player_score_same() {
                //given
                Dealer dealer = new Dealer(new StandState(new Hand(List.of(CLUB_JACK, CLUB_NINE))));

                // when
                Result result = Result.checkPlayerResult(player, dealer);
                int playerProfit = player.getProfit(result);

                //then
                assertThat(playerProfit).isEqualTo(0);
            }
        }
    }
}
