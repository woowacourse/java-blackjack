package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameResultTest {
    private static final int BLACKJACK = 21;
    private static final int LESS_THAN_BLACKJACK = 20;
    private static final int BURST = 22;
    private static final String PLAYER_NAME = "tori";

    private GameResult gameResult;

    @BeforeEach
    void setup() {
        GamePlayer gamePlayer = new GamePlayer(new Dealer(), Players.from(List.of("tori", "name2", "name3")));
        Game game = Game.from(gamePlayer);
        gameResult = new GameResult(game);
    }

    @DisplayName("GameResult의 수는 GamePlayer의 수와 같다.")
    @Test
    void Should_Create_When_NewGameResult() {
        GamePlayer gamePlayer = new GamePlayer(new Dealer(), Players.from(List.of("tori", "name2", "name3")));
        Game game = Game.from(gamePlayer);
        GameResult gameResult = new GameResult(game);

        assertThat(gameResult.getDealerResult().size()).isEqualTo(3);
    }

    @DisplayName("플레이어와 딜러의 점수를 통해 승패를 결정한다.")
    @Nested
    class calculate {
        @DisplayName("플레이어가 블랙잭일 때")
        @Nested
        class playerIsBlackJack {
            @DisplayName("딜러가 블랙잭이면 플레이어의 결과는 무이다.")
            @Test
            void Should_Draw_When_DealerBlackJack() {
                gameResult.calculateVictoryOrDefeatWithDealerAndPlayer(BLACKJACK, BLACKJACK, PLAYER_NAME);
                String playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);
                assertThat(playerResult).isEqualTo("무");
            }

            @DisplayName("딜러가 블랙잭이 아니면 플레이어의 결과는 승이다.")
            @Test
            void Should_WIN_When_DealerNotBlackJack() {
                gameResult.calculateVictoryOrDefeatWithDealerAndPlayer(LESS_THAN_BLACKJACK, BLACKJACK, PLAYER_NAME);
                String playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);
                assertThat(playerResult).isEqualTo("승");
            }
        }

        @DisplayName("플레이어가 블랙잭보다 작을 때")
        @Nested
        class playerLessThanBlackJack {
            @DisplayName("딜러가 블랙잭이면 플레이어의 결과는 패이다.")
            @Test
            void Should_LOSE_When_DealerBlackJack() {
                gameResult.calculateVictoryOrDefeatWithDealerAndPlayer(BLACKJACK, LESS_THAN_BLACKJACK, PLAYER_NAME);
                String playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);
                assertThat(playerResult).isEqualTo("패");
            }

            @DisplayName("딜러가 버스트이면 플레이어의 결과는 승이다.")
            @Test
            void Should_WIN_When_DealerBurst() {
                gameResult.calculateVictoryOrDefeatWithDealerAndPlayer(BURST, LESS_THAN_BLACKJACK, PLAYER_NAME);
                String playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);
                assertThat(playerResult).isEqualTo("승");
            }

            @DisplayName("딜러가 블랙잭보다 작을 때")
            @Nested
            class dealerLessThanBlackJack {
                @DisplayName("플레이어의 합이 딜러보다 높으면 플레이어의 결과는 승이다.")
                @Test
                void Should_WIN_When_PlayerMoreThanDealer() {
                    gameResult.calculateVictoryOrDefeatWithDealerAndPlayer(
                            LESS_THAN_BLACKJACK - 1, LESS_THAN_BLACKJACK, PLAYER_NAME);
                    String playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);
                    assertThat(playerResult).isEqualTo("승");
                }

                @DisplayName("플레이어의 합이 딜러보다 작으면 플레이어의 결과는 패이다.")
                @Test
                void Should_LOSE_When_PlayerLessThanDealer() {
                    gameResult.calculateVictoryOrDefeatWithDealerAndPlayer(
                            LESS_THAN_BLACKJACK - 1, LESS_THAN_BLACKJACK - 1, PLAYER_NAME);
                    String playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);
                    assertThat(playerResult).isEqualTo("패");
                }
            }
        }

        @DisplayName("플레이어가 버스트일 때")
        @Nested
        class playerBurst {
            @DisplayName("딜러가 블랙잭이면 플레이어의 결과는 패이다.")
            @Test
            void Should_LOSE_When_DealerBlackJack() {
                gameResult.calculateVictoryOrDefeatWithDealerAndPlayer(BLACKJACK, BURST, PLAYER_NAME);
                String playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);
                assertThat(playerResult).isEqualTo("패");
            }

            @DisplayName("딜러가 버스트이면 플레이어의 결과는 패이다.")
            @Test
            void Should_LOSE_When_DealerBurst() {
                gameResult.calculateVictoryOrDefeatWithDealerAndPlayer(BURST, BURST, PLAYER_NAME);
                String playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);
                assertThat(playerResult).isEqualTo("패");
            }

            @DisplayName("딜러가 블랙잭보다 작으면 플레이어의 결과는 패이다.")
            @Test
            void Should_LOSE_When_DealerLessThanBlackJack() {
                gameResult.calculateVictoryOrDefeatWithDealerAndPlayer(LESS_THAN_BLACKJACK, BURST, PLAYER_NAME);
                String playerResult = gameResult.getPlayersResult().get(PLAYER_NAME);
                assertThat(playerResult).isEqualTo("패");
            }
        }

    }
}
