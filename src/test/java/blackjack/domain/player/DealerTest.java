package blackjack.domain.player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Result;
import blackjack.domain.card.Type;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("카드 뭉치를 비교했을때 딜러는 승리한다.")
    @Test
    void testMakeResultWin() {
        Dealer dealer = new Dealer("pobi");
        dealer.draw(new Card(Type.HEART, Denomination.THREE));
        dealer.draw(new Card(Type.HEART, Denomination.TEN));

        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.THREE),
                new Card(Type.CLUB, Denomination.FOUR)
            )
        );
        dealer.matchCards(cards);

        assertThat(dealer.getResultCount(Result.WIN)).isEqualTo(1);
    }

    @DisplayName("카드 뭉치를 비교했을때 딜러는 무승부가 된다.")
    @Test
    void testMakeResultDraw() {
        Dealer dealer = new Dealer("pobi");
        dealer.draw(new Card(Type.HEART, Denomination.THREE));
        dealer.draw(new Card(Type.HEART, Denomination.FOUR));

        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.THREE),
                new Card(Type.CLUB, Denomination.FOUR)
            )
        );
        dealer.matchCards(cards);
        assertThat(dealer.getResultCount(Result.DRAW)).isEqualTo(1);
    }

    @DisplayName("카드 뭉치를 비교했을때 딜러는 패배한다.")
    @Test
    void testMakeResultLose() {
        Dealer dealer = new Dealer("pobi");
        dealer.draw(new Card(Type.HEART, Denomination.THREE));
        dealer.draw(new Card(Type.HEART, Denomination.FOUR));

        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.THREE),
                new Card(Type.CLUB, Denomination.TEN)
            )
        );
        dealer.matchCards(cards);
        assertThat(dealer.getResultCount(Result.LOSE)).isEqualTo(1);
    }

    @DisplayName("딜러는 카드 점수 합이 16이하면 추가로 카드를 드로우 할 수 있다.")
    @Test
    void testCanDraw() {
        Dealer dealer = new Dealer("딜");
        dealer.draw(new Card(Type.CLUB, Denomination.TEN));
        dealer.draw(new Card(Type.CLUB, Denomination.THREE));

        assertThat(dealer.canDraw()).isEqualTo(true);
    }

    @DisplayName("딜러는 카드 점수 합이 16초과 카드를 드로우 할 수 없다.")
    @Test
    void testCanNotDraw() {
        Dealer dealer = new Dealer("딜");
        dealer.draw(new Card(Type.CLUB, Denomination.TEN));
        dealer.draw(new Card(Type.CLUB, Denomination.SEVEN));

        assertThat(dealer.canDraw()).isEqualTo(false);
    }
}
