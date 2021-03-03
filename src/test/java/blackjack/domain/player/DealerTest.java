package blackjack.domain.player;

import blackjack.domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @DisplayName("카드 받기 테스트")
    @Test
    void drawCard() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        dealer.draw(deck, 0);

        assertThat(dealer.cards().getCard(0)).isEqualTo(new Card(Type.SPADE, Denomination.ACE));
    }

    @DisplayName("승패를 결정한다.")
    @Test
    void calculateResult() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Player player = new Player();

        dealer.draw(deck, 0); // A 스페이드
        dealer.draw(deck, 13); // A 다이아몬드

        player.draw(deck, 26); // A 하트
        player.draw(deck, 47); // 9 클로버

        assertThat(dealer.compare(player)).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어가 블랙잭으로 패배한다.")
    @Test
    void loseByBlackjack() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Player player = new Player();

        dealer.draw(deck, 0); // A 스페이드
        dealer.draw(deck, 49); // J 클로

        player.draw(deck, 26); // A 하트
        player.draw(deck, 46); // 8 클로버
        player.draw(deck, 40); // 2 클로버

        assertThat(dealer.compare(player)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어와 딜러가 블랙잭 무승다.")
    @Test
    void drawByBlackjack() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Player player = new Player();

        dealer.draw(deck, 0); // A 스페이드
        dealer.draw(deck, 49); // J 클로

        player.draw(deck, 13); // A 다이아몬
        player.draw(deck, 48); // 10 클로

        assertThat(dealer.compare(player)).isEqualTo(Result.DRAW);
    }

    @DisplayName("플레이어가 블랙잭으로 승리한다.")
    @Test
    void winByBlackjack() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Player player = new Player();

        dealer.draw(deck, 26); // A 하트
        dealer.draw(deck, 46); // 8 클로버
        dealer.draw(deck, 40); // 2 클로버

        player.draw(deck, 0); // A 스페이드
        player.draw(deck, 49); // J 클로

        assertThat(dealer.compare(player)).isEqualTo(Result.BLACKJACK_WIN);
    }

    @DisplayName("플레이어가 패배한다.")
    @Test
    void lose() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Player player = new Player();

        dealer.draw(deck, 3); // 4 스페이드
        dealer.draw(deck, 7); // 8 스페이드
        dealer.draw(deck, 17); // 5 다이아몬드

        player.draw(deck, 16); // 3 다이아몬드
        player.draw(deck, 20); // 8 다이아몬드
        player.draw(deck, 29); // 4 다이아몬드

        assertThat(dealer.compare(player)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어와 딜러가 무승부다.")
    @Test
    void draw() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Player player = new Player();

        dealer.draw(deck, 3); // 4 스페이드
        dealer.draw(deck, 7); // 8 스페이드
        dealer.draw(deck, 17); // 5 다이아몬드
        dealer.draw(deck, 0); // A 스페이드

        player.draw(deck, 15); // 3 다이아몬드
        player.draw(deck, 39); // A 클로버
        player.draw(deck, 29); // 4 하트

        assertThat(dealer.compare(player)).isEqualTo(Result.DRAW);
    }

    @DisplayName("플레이어가 승리한다.")
    @Test
    void win() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Player player = new Player();

        dealer.draw(deck, 8); // 9 스페이드
        dealer.draw(deck, 24); // Q 다이아몬드
        dealer.draw(deck, 39); // A 클로버

        player.draw(deck, 0); // A 스페이드
        player.draw(deck, 13); // A 다이아몬드
        player.draw(deck, 26); // A 하트
        player.draw(deck, 7); // 8 스페이드

        assertThat(dealer.compare(player)).isEqualTo(Result.WIN);
    }


    @DisplayName("딜러의 게임 결과를 계산한다.")
    @Test
    void testDealerGameResult() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Player player1 = new Player();
        Player player2 = new Player();

        dealer.draw(deck, 8); // 9 스페이드
        dealer.draw(deck, 24); // Q 다이아몬드
        dealer.draw(deck, 39); // A 클로버

        player1.draw(deck, 0); // A 스페이드
        player1.draw(deck, 13); // A 다이아몬드
        player1.draw(deck, 26); // A 하트
        player1.draw(deck, 7); // 8 스페이드

        player2.draw(deck, 0); // A 스페이드
        player2.draw(deck, 13); // A 다이아몬드
        player2.draw(deck, 26); // A 하트
        player2.draw(deck, 7); // 8 스페이드

        dealer.compare(player1);
        dealer.compare(player2);

        assertThat(dealer.getRecord()).isEqualTo(Arrays.asList(Result.LOSE, Result.LOSE));
    }
}
