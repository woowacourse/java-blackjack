import card.Card;
import card.CardNumber;
import card.CardType;
import participant.Dealer;
import participant.GameResult;
import participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Test
    @DisplayName("두 점수 사이 승패 여부 결정 테스트")
    void test() {
        // given
        Player player = new Player("율무");
        player.hit(new Card(CardType.DIAMOND, CardNumber.TEN));
        player.hit(new Card(CardType.DIAMOND, CardNumber.JACK));

        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardType.SPADE, CardNumber.THREE));
        dealer.hit(new Card(CardType.CLOVER, CardNumber.THREE));

        // when
        GameResult result = GameResult.judge(player, dealer);

        // then
        Assertions.assertThat(result)
                .isEqualTo(GameResult.WIN);
    }
}
