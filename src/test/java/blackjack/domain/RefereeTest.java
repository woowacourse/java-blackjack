package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPicker;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RefereeTest {
    Referee referee;
    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setupGamer() {
        player = new Player("lemone");
        dealer = new Dealer();
        referee = new Referee(dealer);
    }


    @Test
    @DisplayName("두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이기는지 테스트한다.")
    void playerWinTest() {
        // given
        CardPicker playerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_QUEEN);
            }
        };
        CardPicker dealerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.SPADE_EIGHT, Card.CLUB_QUEEN);
            }
        };

        // when
        player.deal(playerCardPicker);
        dealer.deal(dealerCardPicker);
        GameResult gameResult = referee.judgeGameResult(player);

        // then
        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어는 자신의 숫자 합이 21을 초과할 경우 패배한다.")
    void playerLoseWhenBurstTest() {
        // given
        CardPicker playerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_QUEEN, Card.CLUB_THREE);
            }
        };
        CardPicker dealerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.SPADE_EIGHT, Card.SPADE_TWO, Card.CLUB_QUEEN);
            }
        };

        // when
        player.deal(playerCardPicker);
        dealer.deal(dealerCardPicker);
        GameResult gameResult = referee.judgeGameResult(player);

        // then
        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러의 합과 플레이어의 합이 같으면 무승부이다.")
    void playerDrawWhenSameBlackjackTest() {
        // given
        CardPicker playerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_SEVEN, Card.CLUB_THREE);
            }
        };
        CardPicker dealerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_SEVEN, Card.CLUB_THREE);
            }
        };

        // when
        player.deal(playerCardPicker);
        dealer.deal(dealerCardPicker);
        GameResult gameResult = referee.judgeGameResult(player);

        // then
        assertThat(gameResult).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("플레이어의 첫 두개의 카드의 합이 21이면 승리한다.")
    void playerDealCardsBlackjackTest() {
        // given
        CardPicker playerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.CLUB_ACE, Card.CLUB_JACK);
            }
        };
        CardPicker dealerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_SEVEN, Card.CLUB_FIVE);
            }
        };

        // when
        player.deal(playerCardPicker);
        dealer.deal(dealerCardPicker);
        GameResult gameResult = referee.judgeGameResult(player);

        // then
        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어의 점수가 딜러보다 낮을 경우 패배한다.")
    void playerLoseTest() {
        // given
        CardPicker playerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.CLUB_TWO, Card.CLUB_JACK);
            }
        };
        CardPicker dealerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.SPADE_NINE, Card.CLUB_SEVEN, Card.CLUB_FIVE);
            }
        };

        // when
        player.deal(playerCardPicker);
        dealer.deal(dealerCardPicker);
        GameResult gameResult = referee.judgeGameResult(player);

        // then
        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 burst이면, 플레이어가 패배한다.")
    void playerDealerAllBustPlayerLoseTest() {
        // given
        CardPicker playerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.CLUB_KING, Card.CLUB_JACK, Card.CLUB_THREE);
            }
        };
        CardPicker dealerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.CLUB_KING, Card.CLUB_JACK, Card.CLUB_THREE);
            }
        };

        // when
        player.deal(playerCardPicker);
        dealer.deal(dealerCardPicker);
        GameResult gameResult = referee.judgeGameResult(player);

        // then
        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 burst이고 플레이가 brust가 아닐 경우, 플레이어가 승리한다.")
    void dealerBurstPlayerNonBurstWinTest() {
        // given
        CardPicker playerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.CLUB_KING, Card.CLUB_JACK);
            }
        };
        CardPicker dealerCardPicker = new CardPicker() {
            @Override
            public List<Card> pick(int count) {
                return List.of(Card.CLUB_KING, Card.CLUB_JACK, Card.CLUB_THREE);
            }
        };

        // when
        player.deal(playerCardPicker);
        dealer.deal(dealerCardPicker);
        GameResult gameResult = referee.judgeGameResult(player);

        // then
        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }
}
