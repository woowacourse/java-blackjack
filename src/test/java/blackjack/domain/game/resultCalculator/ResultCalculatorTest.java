package blackjack.domain.game.resultCalculator;

import blackjack.domain.card.Card;
import blackjack.domain.card.Letter;
import blackjack.domain.card.Shape;
import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ResultCalculatorTest {
    @Test
    @DisplayName("딜러가 버스트일때 플레이어가 버스트가 아닐때 테스트")
    void resultCalculatorWithBustDealerNotBustPlayer(){
        Player player = new Player(new Name("test"));
        Dealer dealer = new Dealer();
        HashMap<Player, WinTieLose> playersResult = new HashMap<>();
        player.drawCard(new Card(Shape.CLOVER, Letter.ACE));
        player.drawCard(new Card(Shape.CLOVER,Letter.TEN));

        dealer.drawCard(new Card(Shape.CLOVER, Letter.TWO));
        dealer.drawCard(new Card(Shape.HEART,Letter.TEN));
        dealer.drawCard(new Card(Shape.DIAMOND,Letter.TEN));
        ResultCalculator resultCalculatorWithBustDealer = new ResultCalculatorWithBustDealer();

        resultCalculatorWithBustDealer.calculateResult(playersResult,player,dealer);
        assertThat(playersResult.get(player)).isEqualTo(WinTieLose.WIN);
    }

    @Test
    @DisplayName("딜러가 버스트일때 플레이어가 버스트일때 테스트")
    void resultCalculatorWithBustDealerBustPlayer(){
        Player player = new Player(new Name("test"));
        Dealer dealer = new Dealer();
        HashMap<Player, WinTieLose> playersResult = new HashMap<>();
        player.drawCard(new Card(Shape.CLOVER, Letter.THREE));
        player.drawCard(new Card(Shape.CLOVER,Letter.TEN));
        player.drawCard(new Card(Shape.CLOVER,Letter.JACK));

        dealer.drawCard(new Card(Shape.CLOVER, Letter.TWO));
        dealer.drawCard(new Card(Shape.HEART,Letter.TEN));
        dealer.drawCard(new Card(Shape.DIAMOND,Letter.TEN));
        ResultCalculator resultCalculatorWithBustDealer = new ResultCalculatorWithBustDealer();

        resultCalculatorWithBustDealer.calculateResult(playersResult,player,dealer);
        assertThat(playersResult.get(player)).isEqualTo(WinTieLose.TIE);
    }

    @Test
    @DisplayName("딜러가 버스트아닐때 플레이어가 버스트가 일때 테스트")
    void resultCalculatorWithNotBustDealerBustPlayer(){
        Player player = new Player(new Name("test"));
        Dealer dealer = new Dealer();
        HashMap<Player, WinTieLose> playersResult = new HashMap<>();

        dealer.drawCard(new Card(Shape.CLOVER, Letter.ACE));
        dealer.drawCard(new Card(Shape.CLOVER,Letter.TEN));

        player.drawCard(new Card(Shape.CLOVER, Letter.TWO));
        player.drawCard(new Card(Shape.HEART,Letter.TEN));
        player.drawCard(new Card(Shape.DIAMOND,Letter.TEN));

        ResultCalculator resultCalculatorWithBustPlayer = new ResultCalculatorWithBustPlayer();

        resultCalculatorWithBustPlayer.calculateResult(playersResult,player,dealer);
        assertThat(playersResult.get(player)).isEqualTo(WinTieLose.LOSE);
    }

    @Test
    @DisplayName("둘다 버스트가 아닐때 딜러가 이기는 경우 테스트")
    void resultCalculatorWithNotParticipantsDealerWin(){
        Player player = new Player(new Name("test"));
        Dealer dealer = new Dealer();
        HashMap<Player, WinTieLose> playersResult = new HashMap<>();

        dealer.drawCard(new Card(Shape.CLOVER, Letter.NINE));
        dealer.drawCard(new Card(Shape.CLOVER,Letter.TEN));

        player.drawCard(new Card(Shape.CLOVER, Letter.TWO));
        player.drawCard(new Card(Shape.HEART,Letter.TEN));


        ResultCalculator resultCalculatorWithNotBust = new ResultCalculatorWithNotBustDealerPlayer();

        resultCalculatorWithNotBust.calculateResult(playersResult,player,dealer);
        assertThat(playersResult.get(player)).isEqualTo(WinTieLose.LOSE);
    }

    @Test
    @DisplayName("둘다 버스트가 아닐때 딜러가 지는 경우 테스트")
    void resultCalculatorWithNotParticipantsDealerLose(){
        Player player = new Player(new Name("test"));
        Dealer dealer = new Dealer();
        HashMap<Player, WinTieLose> playersResult = new HashMap<>();

        player.drawCard(new Card(Shape.CLOVER, Letter.NINE));
        player.drawCard(new Card(Shape.CLOVER,Letter.TEN));

        dealer.drawCard(new Card(Shape.CLOVER, Letter.TWO));
        dealer.drawCard(new Card(Shape.HEART,Letter.TEN));


        ResultCalculator resultCalculatorWithNotBust = new ResultCalculatorWithNotBustDealerPlayer();

        resultCalculatorWithNotBust.calculateResult(playersResult,player,dealer);
        assertThat(playersResult.get(player)).isEqualTo(WinTieLose.WIN);
    }
}
