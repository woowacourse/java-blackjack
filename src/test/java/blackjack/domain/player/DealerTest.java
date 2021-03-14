package blackjack.domain.player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Type;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 카드 점수 합이 16이하면 추가로 카드를 드로우 할 수 있다.")
    @Test
    void testCanDraw() {
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Type.CLUB, Denomination.TEN));
        dealer.draw(new Card(Type.CLUB, Denomination.THREE));

        assertThat(dealer.canDraw()).isEqualTo(true);
    }

    @DisplayName("딜러는 카드 점수 합이 16초과시 카드를 드로우 할 수 없다.")
    @Test
    void testCanNotDraw() {
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Type.CLUB, Denomination.TEN));
        dealer.draw(new Card(Type.CLUB, Denomination.SEVEN));

        assertThat(dealer.canDraw()).isEqualTo(false);
    }

    @DisplayName("플레이어 두 명이 1000원씩 배팅을 해서 둘 다 블랙잭으로 승리한다면, 딜러의 수익은 -3000이다.")
    @Test
    void dealerProfitCalculateTest() {
        Dealer dealer = new Dealer();

        Cards playerCards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.ACE),
                new Card(Type.CLUB, Denomination.TEN),
                new Card(Type.CLUB, Denomination.ACE),
                new Card(Type.CLUB, Denomination.TEN)
            )
        );
        Deck playerDeck = new Deck(playerCards);

        Player player1 = new Player("플레이어1");
        player1.addMoney(new Money(1000));
        player1.initialDraw(playerDeck);

        Player player2 = new Player("플레이어2");
        player2.addMoney(new Money(1000));
        player2.initialDraw(playerDeck);

        Money dealerProfit = dealer.profit(new Players(Arrays.asList(player1, player2)));
        assertThat(dealerProfit.getValue()).isEqualTo(-3000);
    }
}
