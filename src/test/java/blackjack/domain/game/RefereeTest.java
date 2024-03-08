package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPicker;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Players;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("심판")
public class RefereeTest {
    private static final String name = "lemone";
    Referee referee;
    CardPicker playerCardPicker, dealerCardPicker;
    private Players players;
    private Dealer dealer;
    private Map<String, GameResult> expectedPlayersResult;

    @BeforeEach
    void setupGamer() {
        players = Players.from(List.of(name));
        dealer = new Dealer();
        referee = new Referee();
        expectedPlayersResult = new HashMap<>();
    }

    @AfterEach
    void assertResult() {
        // when
        players.forEach(player -> player.deal(playerCardPicker));
        dealer.deal(dealerCardPicker);
        referee.calculatePlayersResults(players, dealer);

        // then
        assertThat(referee.getPlayersResults()).isEqualTo(expectedPlayersResult);
    }


    @Test
    @DisplayName("두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이기는지 테스트한다.")
    void playerWinTest() {
        // given
        playerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_QUEEN);
            }
        };
        dealerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.SPADE_EIGHT, Card.CLUB_QUEEN);
            }
        };
        expectedPlayersResult.put(name, GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어는 자신의 숫자 합이 21을 초과할 경우 패배한다.")
    void playerLoseWhenBustTest() {
        // given
        playerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_QUEEN, Card.CLUB_THREE);
            }
        };
        dealerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.SPADE_EIGHT, Card.SPADE_TWO, Card.CLUB_QUEEN);
            }
        };
        expectedPlayersResult.put(name, GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러의 합과 플레이어의 합이 같으면 무승부이다.")
    void playerDrawWhenSameBlackjackTest() {
        // given
        playerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_SEVEN, Card.CLUB_THREE);
            }
        };
        dealerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_SEVEN, Card.CLUB_THREE);
            }
        };
        expectedPlayersResult.put(name, GameResult.DRAW);
    }

    @Test
    @DisplayName("플레이어의 첫 두개의 카드의 합이 21이면 승리한다.")
    void playerDealCardsBlackjackTest() {
        // given
        playerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.CLUB_ACE, Card.CLUB_JACK);
            }
        };
        dealerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_SEVEN, Card.CLUB_FIVE);
            }
        };
        expectedPlayersResult.put(name, GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어의 점수가 딜러보다 낮을 경우 패배한다.")
    void playerLoseTest() {
        // given
        playerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.CLUB_TWO, Card.CLUB_JACK);
            }
        };
        dealerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_SEVEN, Card.CLUB_FIVE);
            }
        };
        expectedPlayersResult.put(name, GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 bust 이면, 플레이어가 패배한다.")
    void playerDealerAllBustPlayerLoseTest() {
        // given
        playerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.CLUB_KING, Card.CLUB_JACK, Card.CLUB_THREE);
            }
        };
        dealerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.CLUB_KING, Card.CLUB_JACK, Card.CLUB_THREE);
            }
        };
        expectedPlayersResult.put(name, GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 bust이고 플레이가 bust가 아닐 경우, 플레이어가 승리한다.")
    void dealerBustPlayerNonBustWinTest() {
        // given
        playerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.CLUB_KING, Card.CLUB_JACK);
            }
        };
        dealerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.CLUB_KING, Card.CLUB_JACK, Card.CLUB_THREE);
            }
        };
        expectedPlayersResult.put(name, GameResult.WIN);
    }
}
