package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameResultTest {
    private static final String PLAYER_NAME = "tori";
    private static final Card JACK_HEARTS = new Card(CardNumber.JACK, CardSymbol.HEARTS);
    private static final Card JACK_CLUBS = new Card(CardNumber.JACK, CardSymbol.CLUBS);
    private static final Card JACK_SPADES = new Card(CardNumber.JACK, CardSymbol.SPADES);
    private static final Card JACK_DIAMONDS = new Card(CardNumber.JACK, CardSymbol.DIAMONDS);
    private static final Card NINE_HEARTS = new Card(CardNumber.NINE, CardSymbol.HEARTS);
    private static final Card TWO_HEARTS = new Card(CardNumber.TWO, CardSymbol.HEARTS);
    private static final Card ACE_HEARTS = new Card(CardNumber.ACE, CardSymbol.HEARTS);
    private static final Card ACE_SPADES = new Card(CardNumber.ACE, CardSymbol.SPADES);
    private static final int BETTING_AMOUNT = 10000;

    private Dealer dealer;
    private Players players;
    private Player player;

    @BeforeEach
    void setup() {
        dealer = new Dealer();
        players = Players.from(List.of("tori"));
        player = players.getPlayers().get(0);
        player.setBettingAmount(new BettingAmount(BETTING_AMOUNT));
    }

    @DisplayName("플레이어와 딜러의 점수를 통해 승패를 결정한다.")
    @Nested
    class Calculate {
        @DisplayName("플레이어가 블랙잭일 때 가진 카드가 2개이면")
        @Nested
        class PlayerIsBlackJackCardsSizeIsTwo {
            @DisplayName("딜러가 블랙잭이면 플레이어는 배팅한 금액을 돌려받는다.")
            @Test
            void Should_Draw_When_DealerBlackJack() {
                player.addCard(JACK_HEARTS);
                player.addCard(ACE_SPADES);
                dealer.addCard(JACK_DIAMONDS);
                dealer.addCard(ACE_HEARTS);

                GameResult gameResult = new GameResult(dealer, players);
                int playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);

                assertThat(playerResult).isEqualTo(0);
            }

            @DisplayName("딜러가 블랙잭이 아니면 플레이어는 1.5배의 금액을 돌려받는다.")
            @Test
            void Should_BlackJack_When_DealerNotBlackJack() {
                player.addCard(JACK_HEARTS);
                player.addCard(ACE_SPADES);
                dealer.addCard(JACK_DIAMONDS);
                dealer.addCard(JACK_SPADES);

                GameResult gameResult = new GameResult(dealer, players);
                int playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);

                assertThat(playerResult).isEqualTo((int) (BETTING_AMOUNT * 1.5));
            }
        }

        @DisplayName("플레이어가 블랙잭일 때 가진 카드가 2개가 아니면")
        @Nested
        class PlayerIsBlackJack {
            @DisplayName("딜러가 블랙잭이면 플레이어는 배팅한 금액을 돌려받는다.")
            @Test
            void Should_Draw_When_DealerBlackJack() {
                player.addCard(JACK_HEARTS);
                player.addCard(NINE_HEARTS);
                player.addCard(TWO_HEARTS);
                dealer.addCard(JACK_DIAMONDS);
                dealer.addCard(ACE_HEARTS);

                GameResult gameResult = new GameResult(dealer, players);
                int playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);

                assertThat(playerResult).isEqualTo(0);
            }

            @DisplayName("딜러가 블랙잭이 아니면 플레이어는 배팅한 금액만큼 돌려받는다.")
            @Test
            void Should_WIN_When_DealerNotBlackJack() {
                player.addCard(JACK_HEARTS);
                player.addCard(NINE_HEARTS);
                player.addCard(TWO_HEARTS);
                dealer.addCard(JACK_DIAMONDS);
                dealer.addCard(JACK_SPADES);

                GameResult gameResult = new GameResult(dealer, players);
                int playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);

                assertThat(playerResult).isEqualTo(BETTING_AMOUNT);
            }
        }

        @DisplayName("플레이어가 블랙잭보다 작을 때")
        @Nested
        class PlayerLessThanBlackJack {
            @DisplayName("딜러가 블랙잭이면 플레이어는 배팅한 금액만큼 잃는다.")
            @Test
            void Should_LOSE_When_DealerBlackJack() {
                player.addCard(JACK_HEARTS);
                player.addCard(JACK_CLUBS);
                dealer.addCard(JACK_DIAMONDS);
                dealer.addCard(ACE_HEARTS);

                GameResult gameResult = new GameResult(dealer, players);
                int playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);

                assertThat(playerResult).isEqualTo(-BETTING_AMOUNT);
            }

            @DisplayName("딜러가 버스트이면 플레이어는 배팅한 금액만큼 돌려받는다.")
            @Test
            void Should_WIN_When_DealerBurst() {
                player.addCard(JACK_HEARTS);
                player.addCard(JACK_CLUBS);
                dealer.addCard(JACK_DIAMONDS);
                dealer.addCard(JACK_SPADES);
                dealer.addCard(TWO_HEARTS);

                GameResult gameResult = new GameResult(dealer, players);
                int playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);

                assertThat(playerResult).isEqualTo(BETTING_AMOUNT);
            }

            @DisplayName("딜러가 블랙잭보다 작을 때")
            @Nested
            class DealerLessThanBlackJack {
                @DisplayName("플레이어의 합이 딜러보다 높으면 플레이어는 배팅한 금액만큼 돌려받는다.")
                @Test
                void Should_WIN_When_PlayerMoreThanDealer() {
                    player.addCard(JACK_HEARTS);
                    player.addCard(JACK_CLUBS);
                    dealer.addCard(JACK_DIAMONDS);
                    dealer.addCard(NINE_HEARTS);

                    GameResult gameResult = new GameResult(dealer,
                            players);
                    int playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);

                    assertThat(playerResult).isEqualTo(BETTING_AMOUNT);
                }

                @DisplayName("플레이어의 합이 딜러보다 작으면 플레이어는 배팅한 금액만큼 잃는다.")
                @Test
                void Should_LOSE_When_PlayerLessThanDealer() {
                    player.addCard(JACK_HEARTS);
                    player.addCard(NINE_HEARTS);
                    dealer.addCard(JACK_DIAMONDS);
                    dealer.addCard(JACK_SPADES);

                    GameResult gameResult = new GameResult(dealer,
                            players);
                    int playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);

                    assertThat(playerResult).isEqualTo(-BETTING_AMOUNT);
                }
            }
        }

        @DisplayName("플레이어가 버스트일 때")
        @Nested
        class PlayerBurst {
            @DisplayName("딜러가 블랙잭이면 플레이어는 배팅한 금액만큼 잃는다.")
            @Test
            void Should_LOSE_When_DealerBlackJack() {
                player.addCard(JACK_HEARTS);
                player.addCard(JACK_CLUBS);
                player.addCard(TWO_HEARTS);
                dealer.addCard(JACK_DIAMONDS);
                dealer.addCard(ACE_HEARTS);

                GameResult gameResult = new GameResult(dealer, players);
                int playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);

                assertThat(playerResult).isEqualTo(-BETTING_AMOUNT);
            }

            @DisplayName("딜러가 버스트이면 플레이어는 배팅한 금액만큼 잃는다.")
            @Test
            void Should_LOSE_When_DealerBurst() {
                player.addCard(JACK_HEARTS);
                player.addCard(JACK_CLUBS);
                player.addCard(TWO_HEARTS);
                dealer.addCard(JACK_DIAMONDS);
                dealer.addCard(JACK_SPADES);
                dealer.addCard(NINE_HEARTS);

                GameResult gameResult = new GameResult(dealer, players);
                int playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);

                assertThat(playerResult).isEqualTo(-BETTING_AMOUNT);
            }

            @DisplayName("딜러가 블랙잭보다 작으면 플레이어는 배팅한 금액만큼 잃는다.")
            @Test
            void Should_LOSE_When_DealerLessThanBlackJack() {
                player.addCard(JACK_HEARTS);
                player.addCard(JACK_CLUBS);
                player.addCard(TWO_HEARTS);
                dealer.addCard(JACK_DIAMONDS);
                dealer.addCard(JACK_SPADES);

                GameResult gameResult = new GameResult(dealer, players);
                int playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);

                assertThat(playerResult).isEqualTo(-BETTING_AMOUNT);
            }
        }
    }
}
