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

class PlayerTest {

    @DisplayName("카드 뭉치를 비교했을때 플레이어는 승리한다.")
    @Test
    void testMakeResultWin() {
        Player player = new Player("pobi");
        player.draw(new Card(Type.HEART, Denomination.THREE));
        player.draw(new Card(Type.HEART, Denomination.TEN));

        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.THREE),
                new Card(Type.CLUB, Denomination.FOUR)
            )
        );

        assertThat(player.matchCards(cards)).isEqualTo(Result.WIN);
    }

    @DisplayName("카드 뭉치를 비교했을때 플레이어는 무승부가 된다.")
    @Test
    void testMakeResultDraw() {
        Player player = new Player("pobi");
        player.draw(new Card(Type.HEART, Denomination.THREE));
        player.draw(new Card(Type.HEART, Denomination.FOUR));

        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.THREE),
                new Card(Type.CLUB, Denomination.FOUR)
            )
        );

        assertThat(player.matchCards(cards)).isEqualTo(Result.DRAW);
    }

    @DisplayName("카드 뭉치를 비교했을때 플레이어는 패배한다.")
    @Test
    void testMakeResultLose() {
        Player player = new Player("pobi");
        player.draw(new Card(Type.HEART, Denomination.THREE));
        player.draw(new Card(Type.HEART, Denomination.FOUR));

        Cards cards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.THREE),
                new Card(Type.CLUB, Denomination.TEN)
            )
        );

        assertThat(player.matchCards(cards)).isEqualTo(Result.LOSE);
    }

}
