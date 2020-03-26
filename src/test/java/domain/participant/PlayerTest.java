package domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Symbol;

class PlayerTest {
    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player = new Player(Name.create("플레이어"), Money.create("0"));
        player.setBettingMoney(Money.create("100"));
        dealer = new Dealer();
    }

    @Test
    @DisplayName("플레이어 혼자 블랙잭일 때 수익으로 (베팅금액*1.5)를 뱉어내는지")
    void isPlayerBlackJack() {
        player.receive(new Card(Symbol.ACE, Shape.CLOVER));
        player.receive(new Card(Symbol.QUEEN, Shape.DIAMOND));
        dealer.receive(new Card(Symbol.FOUR, Shape.DIAMOND));
        player.setBlackJack(player.calculateScore());
        dealer.setBlackJack(dealer.calculateScore());
        assertThat(player.computeProfit(dealer)).isEqualTo(150);
    }

    @Test
    @DisplayName("플레이어 딜러 둘 다 블랙잭일 때 수익으로 0을 뱉어내는지")
    void isPlayerDealerBlackJack() {
        player.receive(new Card(Symbol.ACE, Shape.CLOVER));
        player.receive(new Card(Symbol.QUEEN, Shape.DIAMOND));
        dealer.receive(new Card(Symbol.ACE, Shape.HEART));
        dealer.receive(new Card(Symbol.QUEEN, Shape.CLOVER));

        player.setBlackJack(player.calculateScore());
        dealer.setBlackJack(dealer.calculateScore());

        assertThat(player.computeProfit(dealer)).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어가 버스트일 때 수익으로 -(베팅금액)를 뱉어내는지")
    void isPlayerBust() {
        player.receive(new Card(Symbol.SEVEN, Shape.DIAMOND));
        player.receive(new Card(Symbol.EIGHT, Shape.SPADE));
        player.receive(new Card(Symbol.EIGHT, Shape.HEART));
        dealer.receive(new Card(Symbol.FOUR, Shape.SPADE));

        player.setBlackJack(player.calculateScore());
        dealer.setBlackJack(dealer.calculateScore());

        assertThat(player.computeProfit(dealer)).isEqualTo(-100);
    }

    @Test
    @DisplayName("플레이어 딜러 모두 버스트일 때 수익으로 -(베팅금액)를 뱉어내는지")
    void isPlayerDealerBust() {
        player.receive(new Card(Symbol.SEVEN, Shape.DIAMOND));
        player.receive(new Card(Symbol.EIGHT, Shape.SPADE));
        player.receive(new Card(Symbol.EIGHT, Shape.HEART));
        dealer.receive(new Card(Symbol.FOUR, Shape.SPADE));
        dealer.receive(new Card(Symbol.QUEEN, Shape.DIAMOND));
        dealer.receive(new Card(Symbol.KING, Shape.SPADE));

        player.setBlackJack(player.calculateScore());
        dealer.setBlackJack(dealer.calculateScore());

        assertThat(player.computeProfit(dealer)).isEqualTo(-100);
    }

    @Test
    @DisplayName("플레이어가 점수가 높을 때 수익으로 +(베팅금액)을 뱉어내는지")
    void isPlayerWin() {
        player.receive(new Card(Symbol.SEVEN, Shape.DIAMOND));
        player.receive(new Card(Symbol.EIGHT, Shape.HEART));
        dealer.receive(new Card(Symbol.FOUR, Shape.SPADE));
        assertThat(player.computeProfit(dealer)).isEqualTo(100);
    }

    @Test
    @DisplayName("플레이어와 딜러 점수가 같을 때 수익으로 0을 뱉어내는지")
    void isPlayerDraw() {
        player.receive(new Card(Symbol.SEVEN, Shape.DIAMOND));
        dealer.receive(new Card(Symbol.SEVEN, Shape.SPADE));
        assertThat(player.computeProfit(dealer)).isEqualTo(0);
    }

}
