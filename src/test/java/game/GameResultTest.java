package game;

import static org.assertj.core.api.Assertions.assertThat;

import card.Card;
import card.CardNumber;
import card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import participant.Dealer;
import participant.Player;

class GameResultTest {

    @Test
    @DisplayName("두 점수 사이 승패 여부 결정 테스트")
    void test1() {
        // given
        Player player = new Player("율무", 10000);
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.JACK));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.THREE));
        dealer.receiveCard(new Card(CardShape.CLOVER, CardNumber.THREE));

        // when
        GameResult result = GameResult.judge(player, dealer);

        // then
        assertThat(result)
                .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("카드의 반대 결과를 출력한다.")
    void test2() {
        // given
        GameResult win = GameResult.WIN;
        GameResult lose = GameResult.LOSE;
        GameResult draw = GameResult.DRAW;

        // when
        GameResult inverseWin = win.inverse();
        GameResult inverseLose = lose.inverse();
        GameResult inverseDraw = draw.inverse();

        // then
        assertThat(inverseWin).isEqualTo(GameResult.LOSE);
        assertThat(inverseLose).isEqualTo(GameResult.WIN);
        assertThat(inverseDraw).isEqualTo(GameResult.DRAW);
    }
}
