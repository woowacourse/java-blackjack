package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @Test
    @DisplayName("딜러의 카드 합보다 플레이어의 카드 합이 높으면 승 판정")
    void winTest() {
        // given
        Dealer dealer = new Dealer();
        Player player = new Player("luke");
        Card card = new Card(Figure.SPADE, Number.THREE);
        Card card2 = new Card(Figure.CLOVER, Number.TWO);

        player.receiveCard(card);
        dealer.receiveCard(card2);

        player.updateScore();
        dealer.updateScore();

        // when & then
        assertThat(GameResult.calculateScore(player, dealer).getStatus())
                .isEqualTo("승");
    }

    @Test
    @DisplayName("딜러의 카드 합보다 플레이어의 카드 합이 낮으면 패 판정")
    void loseTest() {
        // given
        Dealer dealer = new Dealer();
        Player player = new Player("luke");
        Card card = new Card(Figure.SPADE, Number.TWO);
        Card card2 = new Card(Figure.CLOVER, Number.THREE);

        player.receiveCard(card);
        dealer.receiveCard(card2);

        player.updateScore();
        dealer.updateScore();

        // when & then
        assertThat(GameResult.calculateScore(player, dealer).getStatus())
                .isEqualTo("패");
    }

    @Test
    @DisplayName("딜러의 카드 합과 플레이어의 카드 합이 같으면 무 판정")
    void drawTest() {
        // given
        Dealer dealer = new Dealer();
        Player player = new Player("luke");
        Card card = new Card(Figure.SPADE, Number.JACK);
        Card card2 = new Card(Figure.SPADE, Number.ACE);
        Card card3 = new Card(Figure.SPADE, Number.KING);
        Card card4 = new Card(Figure.DIAMOND, Number.ACE);

        player.receiveCard(card);
        player.receiveCard(card2);
        dealer.receiveCard(card3);
        dealer.receiveCard(card4);

        player.updateScore();
        dealer.updateScore();

        // when & then
        assertThat(GameResult.calculateScore(player, dealer).getStatus())
                .isEqualTo("무");
    }

    @Test
    @DisplayName("딜러가 버스트이고, 플레이어가 버스트가 아니면 승 판정")
    void isWinWhenDealerIsBurstTest() {
        // given
        Dealer dealer = new Dealer();
        Player player = new Player("luke");
        Card card = new Card(Figure.SPADE, Number.THREE);
        Card card2 = new Card(Figure.SPADE, Number.TEN);
        Card card3 = new Card(Figure.SPADE, Number.KING);
        Card card4 = new Card(Figure.CLOVER, Number.JACK);

        player.receiveCard(card);
        dealer.receiveCard(card2);
        dealer.receiveCard(card3);
        dealer.receiveCard(card4);

        player.updateScore();
        dealer.updateScore();

        // when & then
        assertThat(GameResult.calculateScore(player, dealer).getStatus())
                .isEqualTo("승");
    }

    @Test
    @DisplayName("딜러가 버스트가 아니고, 플레이어가 버스트라면 패 판정")
    void isLoseWhenPlayerIsBurstTest() {
        // given
        Dealer dealer = new Dealer();
        Player player = new Player("luke");
        Card card = new Card(Figure.SPADE, Number.JACK);
        Card card2 = new Card(Figure.SPADE, Number.TEN);
        Card card3 = new Card(Figure.SPADE, Number.KING);
        Card card4 = new Card(Figure.CLOVER, Number.JACK);

        player.receiveCard(card);
        player.receiveCard(card2);
        player.receiveCard(card3);
        dealer.receiveCard(card4);

        player.updateScore();
        dealer.updateScore();

        // when & then
        assertThat(GameResult.calculateScore(player, dealer).getStatus())
                .isEqualTo("패");
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 버스트인 경우 무 판정")
    void isDrawWhenBothIsBurstTest() {
        // given
        Dealer dealer = new Dealer();
        Player player = new Player("luke");
        Card card = new Card(Figure.SPADE, Number.JACK);
        Card card2 = new Card(Figure.SPADE, Number.TEN);
        Card card3 = new Card(Figure.SPADE, Number.KING);
        Card card4 = new Card(Figure.SPADE, Number.JACK);
        Card card5 = new Card(Figure.SPADE, Number.TEN);
        Card card6 = new Card(Figure.SPADE, Number.KING);

        player.receiveCard(card);
        player.receiveCard(card2);
        player.receiveCard(card3);
        dealer.receiveCard(card4);
        dealer.receiveCard(card5);
        dealer.receiveCard(card6);

        player.updateScore();
        dealer.updateScore();

        // when & then
        assertThat(GameResult.calculateScore(player, dealer).getStatus())
                .isEqualTo("무");
    }
}