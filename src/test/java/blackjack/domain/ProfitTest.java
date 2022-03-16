package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Profit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitTest {

    private Player player;
    private Dealer dealer;

    @BeforeEach
    void init() {
        player = new Player("pobi");
        player.betMoney(1000);

        dealer = new Dealer();
    }


    @Test
    @DisplayName("플레이어만 블랙잭인 경우 베팅금액의 1.5배를 돌려받는다")
    void blackjack() {
        player.addCard(new Card(Symbol.SPADE, Denomination.KING));
        player.addCard(new Card(Symbol.HEART, Denomination.ACE));
        player.computeTotalScore();

        dealer.addCard(new Card(Symbol.DIAMOND, Denomination.KING));
        dealer.addCard(new Card(Symbol.CLOVER, Denomination.QUEEN));
        dealer.computeTotalScore();

        assertThat(Profit.of(player, dealer).getValue()).isEqualTo(1500);
    }

    @Test
    @DisplayName("딜러가 bust인 경우 베팅금액을 그대로 돌려받는다")
    void dealerBust() {
        player.addCard(new Card(Symbol.SPADE, Denomination.KING));
        player.addCard(new Card(Symbol.HEART, Denomination.QUEEN));
        player.computeTotalScore();

        dealer.addCard(new Card(Symbol.DIAMOND, Denomination.KING));
        dealer.addCard(new Card(Symbol.CLOVER, Denomination.QUEEN));
        dealer.addCard(new Card(Symbol.SPADE, Denomination.THREE));
        dealer.computeTotalScore();

        assertThat(Profit.of(player, dealer).getValue()).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어가 bust인 경우 베팅금액을 돌려받지 못한다")
    void playerBust() {
        player.addCard(new Card(Symbol.SPADE, Denomination.KING));
        player.addCard(new Card(Symbol.HEART, Denomination.QUEEN));
        player.addCard(new Card(Symbol.SPADE, Denomination.THREE));
        player.computeTotalScore();

        dealer.addCard(new Card(Symbol.DIAMOND, Denomination.KING));
        dealer.addCard(new Card(Symbol.CLOVER, Denomination.QUEEN));
        dealer.computeTotalScore();

        assertThat(Profit.of(player, dealer).getValue()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("둘다 블랙잭인 경우 베팅금액을 그대로 돌려받는다")
    void bothBlackjack() {
        player.addCard(new Card(Symbol.SPADE, Denomination.KING));
        player.addCard(new Card(Symbol.HEART, Denomination.ACE));
        player.computeTotalScore();

        dealer.addCard(new Card(Symbol.DIAMOND, Denomination.KING));
        dealer.addCard(new Card(Symbol.CLOVER, Denomination.ACE));
        dealer.computeTotalScore();

        assertThat(Profit.of(player, dealer).getValue()).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어가 승리한 경우 베팅금액을 그대로 돌려받는다")
    void win() {
        player.addCard(new Card(Symbol.SPADE, Denomination.KING));
        player.addCard(new Card(Symbol.HEART, Denomination.QUEEN));
        player.computeTotalScore();

        dealer.addCard(new Card(Symbol.DIAMOND, Denomination.EIGHT));
        dealer.addCard(new Card(Symbol.CLOVER, Denomination.NINE));
        dealer.computeTotalScore();

        assertThat(Profit.of(player, dealer).getValue()).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어가 패배한 경우 베팅금액을 돌려받지 못한다")
    void lose() {
        player.addCard(new Card(Symbol.SPADE, Denomination.EIGHT));
        player.addCard(new Card(Symbol.HEART, Denomination.NINE));
        player.computeTotalScore();

        dealer.addCard(new Card(Symbol.DIAMOND, Denomination.KING));
        dealer.addCard(new Card(Symbol.CLOVER, Denomination.QUEEN));
        dealer.computeTotalScore();

        assertThat(Profit.of(player, dealer).getValue()).isEqualTo(-1000);
    }
}
