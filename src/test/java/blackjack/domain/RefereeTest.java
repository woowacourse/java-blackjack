package blackjack.domain;

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
}
