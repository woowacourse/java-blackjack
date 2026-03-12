package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {
    @Test
    void 게임_시작시_플레이어와_딜러는_각각_두장의_카드를_받는다() {
        Players players = new Players(List.of("pobi", "jason"));
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        BlackJackGame game = new BlackJackGame(players, dealer, deck);

        game.initDraw();

        assertThat(players.getPlayers().get(0).getCardCount()).isEqualTo(2);
        assertThat(players.getPlayers().get(1).getCardCount()).isEqualTo(2);
        assertThat(dealer.getCardCount()).isEqualTo(2);
    }

    @Disabled("현재 Deck은 내부에서 shuffle 되므로 원하는 시작 카드(A, K)를 결정적으로 보장할 수 없다.")
    @Test
    void 시작_카드가_A와_K이면_플레이어는_블랙잭이다_Test() {
        Players players = new Players(List.of("pobi"));
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer, deck);

        blackJackGame.initDraw();

        Player player = players.getPlayers().get(0);

        // 현재 구조에서는 player가 A, K를 받는다고 보장할 수 없다.
        // 따라서 이 테스트를 통과시키려면 테스트용 카드 순서를 주입할 수 있는 Deck 구조가 필요하다.
        //assertThat(player.isBlackJack()).isTrue();
    }


    @Test
    void 시작_카드가_A와_K이면_플레이어는_블랙잭이다() {
        Players players = new Players(List.of("pobi"));
        Player player = players.getPlayers().get(0);
        Dealer dealer = new Dealer();

        Deck deck = new Deck(List.of(
                new Card(CardPoint.TWO, CardPattern.CLUB),
                new Card(CardPoint.THREE, CardPattern.DIAMOND),
                new Card(CardPoint.NINE, CardPattern.HEART),   // 딜러 두 번째 카드
                new Card(CardPoint.KING, CardPattern.SPADE),   // 플레이어 두 번째 카드
                new Card(CardPoint.FIVE, CardPattern.CLUB),    // 딜러 첫 번째 카드
                new Card(CardPoint.ACE, CardPattern.HEART)     // 플레이어 첫 번째 카드
        ));

        BlackJackGame blackJackGame = new BlackJackGame(players, dealer, deck);

        blackJackGame.initDraw();

        assertThat(player.isBlackJack()).isTrue();
    }

}
