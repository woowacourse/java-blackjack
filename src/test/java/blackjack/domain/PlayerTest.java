package blackjack.domain;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {

    @Test
    @DisplayName("플레이어가 버스트 상태인지 알려준다")
    void isPlayerBust() {
        Player player = new Player("pobi", 10000);
        player.receiveCards(
                List.of(Card.valueOf(CardNumber.JACK, Symbol.SPADE),
                        Card.valueOf(CardNumber.QUEEN, Symbol.SPADE),
                        Card.valueOf(CardNumber.KING, Symbol.SPADE)));

        assertThat(player.isFinished()).isTrue();
    }

    @Test
    @DisplayName("플레이어만 블랙잭이면 블랙잭 승리")
    void playerHasBlackjack_winBlackjack() {
        Player player = new Player("dog", 10000);
        player.receiveCards(
                List.of(Card.valueOf(CardNumber.ACE, Symbol.SPADE),
                        Card.valueOf(CardNumber.QUEEN, Symbol.SPADE)));
        Dealer dealer = new Dealer();
        dealer.receiveCards(
                List.of(Card.valueOf(CardNumber.TEN, Symbol.SPADE),
                        Card.valueOf(CardNumber.SEVEN, Symbol.SPADE)));
        assertThat(player.judgeResult(dealer)).isEqualTo(GameResult.WIN_BLACKJACK);
    }

    @Test
    @DisplayName("딜러만 버스트면 플레이어가 승리")
    void onlyDealerBust_playerWin() {
        Player player = new Player("dog", 10000);
        player.receiveCards(
                List.of(Card.valueOf(CardNumber.TEN, Symbol.SPADE),
                        Card.valueOf(CardNumber.QUEEN, Symbol.SPADE)));
        Dealer dealer = new Dealer();
        dealer.receiveCards(
                List.of(Card.valueOf(CardNumber.KING, Symbol.SPADE),
                        Card.valueOf(CardNumber.SEVEN, Symbol.SPADE),
                        Card.valueOf(CardNumber.FIVE, Symbol.SPADE)));
        assertThat(player.judgeResult(dealer)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("둘 다 버스트가 아니고 플레이어의 합이 크면 플레이어가 승리")
    void bothNotBust_playerTotalLarger_playerWin() {
        Player player = new Player("dog", 10000);
        player.receiveCards(
                List.of(Card.valueOf(CardNumber.TEN, Symbol.SPADE),
                        Card.valueOf(CardNumber.SEVEN, Symbol.SPADE),
                        Card.valueOf(CardNumber.THREE, Symbol.SPADE)));

        Dealer dealer = new Dealer();
        dealer.receiveCards(
                List.of(Card.valueOf(CardNumber.TEN, Symbol.SPADE),
                        Card.valueOf(CardNumber.SEVEN, Symbol.SPADE),
                        Card.valueOf(CardNumber.FIVE, Symbol.CLOVER)));
        assertThat(player.judgeResult(dealer)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("둘 다 버스트가 아니고 플레이어의 합이 작으면 플레이어가 패배")
    void bothNotBust_playerTotalSmaller_playerLose() {
        Player player = new Player("dog", 10000);
        player.receiveCards(
                List.of(Card.valueOf(CardNumber.TEN, Symbol.SPADE),
                        Card.valueOf(CardNumber.SIX, Symbol.SPADE),
                        Card.valueOf(CardNumber.TWO, Symbol.CLOVER)));

        Dealer dealer = new Dealer();
        dealer.receiveCards(
                List.of(Card.valueOf(CardNumber.TEN, Symbol.SPADE),
                        Card.valueOf(CardNumber.SIX, Symbol.SPADE),
                        Card.valueOf(CardNumber.THREE, Symbol.CLOVER)));
        assertThat(player.judgeResult(dealer)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러, 플레이어 점수가 동일하면 무승부")
    void bothNotBust_equalScore_draw() {
        Player player = new Player("dog", 10000);
        player.receiveCards(
                List.of(Card.valueOf(CardNumber.SIX, Symbol.SPADE),
                        Card.valueOf(CardNumber.SEVEN, Symbol.SPADE),
                        Card.valueOf(CardNumber.FIVE, Symbol.CLOVER)));

        Dealer dealer = new Dealer();
        dealer.receiveCards(
                List.of(Card.valueOf(CardNumber.THREE, Symbol.SPADE),
                        Card.valueOf(CardNumber.FOUR, Symbol.SPADE),
                        Card.valueOf(CardNumber.ACE, Symbol.CLOVER)));
        assertThat(player.judgeResult(dealer)).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("둘 다 블랙잭이면 무승부")
    void bothBlackjack_draw() {
        Player player = new Player("dog", 10000);
        player.receiveCards(
                List.of(Card.valueOf(CardNumber.ACE, Symbol.SPADE),
                        Card.valueOf(CardNumber.JACK, Symbol.SPADE)));
        Dealer dealer = new Dealer();
        dealer.receiveCards(
                List.of(Card.valueOf(CardNumber.ACE, Symbol.SPADE),
                        Card.valueOf(CardNumber.KING, Symbol.SPADE)));
        assertThat(player.judgeResult(dealer)).isEqualTo(GameResult.DRAW);
    }

}
