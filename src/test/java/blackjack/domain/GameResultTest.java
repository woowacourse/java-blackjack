package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.pattern.Denomination;
import blackjack.domain.card.pattern.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    //플21 | 딜 20(승), 플 20 | 딜 22(승)
    // 플21 | 딜 21(무)
    // 플22 | 딜 21(패), 플20 | 딜21(패), 플22 | 딜 23(패)

    @Test
    @DisplayName("플레이어의 점수와 딜러의 점수 모두 21보다 큰 경우")
    void findLoseResultGamerPointAndDealerPointOutBlackjack() {
        //given
        Dealer dealer = new Dealer();
        get_22_Point(dealer);

        Gamer gamer = new Gamer("judy");
        get_23_Point(gamer);
        //then
        assertThat(GameResult.findResult(dealer.calculateResult(), gamer.calculateResult()))
            .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어의 점수가 21보다 작거나 같지만, 딜러의 점수보다 작은 경우")
    void findLoseResultGamerPointInBlackJackAndLowerDealerPoint() {
        //given
        Dealer dealer = new Dealer();
        get_21_Point(dealer);

        Gamer gamer = new Gamer("judy");
        get_20_Point(gamer);
        //then
        assertThat(GameResult.findResult(dealer.calculateResult(), gamer.calculateResult()))
            .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어의 점수가 21보다 큰 경우")
    void findLoseResultGamerPointOutBlackJack() {
        //given
        Dealer dealer = new Dealer();
        get_21_Point(dealer);

        Gamer gamer = new Gamer("judy");
        get_22_Point(gamer);
        //then
        assertThat(GameResult.findResult(dealer.calculateResult(), gamer.calculateResult()))
            .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어의 점수가 21보다 같거나 작고, 딜러의 점수가 21보다 큰 경우")
    void findDrawResult() {
        //given
        Dealer dealer = new Dealer();
        get_21_Point(dealer);

        Gamer gamer = new Gamer("judy");
        get_21_Point(gamer);
        //then
        assertThat(GameResult.findResult(dealer.calculateResult(), gamer.calculateResult()))
            .isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("플레이어의 점수가 21보다 같거나 작고, 딜러의 점수가 21보다 큰 경우")
    void findWinningResultDealerPointOutBlackJack() {
        //given
        Dealer dealer = new Dealer();
        get_22_Point(dealer);

        Gamer gamer = new Gamer("judy");
        get_21_Point(gamer);
        //then
        assertThat(GameResult.findResult(dealer.calculateResult(), gamer.calculateResult()))
            .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어의 점수가 21보다 같거나 작고, 딜러의 점수가 플레이어의 점수보다 작은 경우")
    void findWinningResultDealerPointInBlackJack() {
        //given
        Dealer dealer = new Dealer();
        get_20_Point(dealer);

        Gamer gamer = new Gamer("judy");
        get_21_Point(gamer);
        //then
        assertThat(GameResult.findResult(dealer.calculateResult(), gamer.calculateResult()))
            .isEqualTo(GameResult.WIN);
    }

    void get_20_Point(Player player) {
        player.receiveCard(new Card(Suit.CLOVER, Denomination.JACK));
        player.receiveCard(new Card(Suit.DIAMOND, Denomination.JACK));
    }

    void get_21_Point(Player player) {
        player.receiveCard(new Card(Suit.HEART, Denomination.JACK));
        player.receiveCard(new Card(Suit.HEART, Denomination.ACE));
    }

    void get_22_Point(Player player) {
        player.receiveCard(new Card(Suit.HEART, Denomination.JACK));
        player.receiveCard(new Card(Suit.SPADE, Denomination.JACK));
        player.receiveCard(new Card(Suit.SPADE, Denomination.TWO));
    }

    void get_23_Point(Player player) {
        player.receiveCard(new Card(Suit.HEART, Denomination.JACK));
        player.receiveCard(new Card(Suit.SPADE, Denomination.JACK));
        player.receiveCard(new Card(Suit.SPADE, Denomination.THREE));
    }

}