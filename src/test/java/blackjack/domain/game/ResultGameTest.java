package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Letter;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class ResultGameTest {
    private Dealer dealer;
    private Participants participants;


    @BeforeEach
    void setting() {
        dealer = new Dealer();
        Map<String,Integer> players = new HashMap<>();
        players.put("pobi",0);
        players.put("crong",0);
        players.put("dali",0);
        participants = new Participants(dealer, players);
    }

    @Test
    @DisplayName("생성자 테스트")
    void constructorTest() {
        assertThatNoException().isThrownBy(() -> new ResultGame(participants));
    }

    @Test
    @DisplayName("플레이어의 결과를 출력하는 테스트")
    void getPlayerResultTest() {
        // given
        ResultGame resultGame = new ResultGame(participants);
        dealer.drawCard(new Card(Shape.CLOVER, Letter.EIGHT));
        Player player = participants.getPlayers().get(0);
        player.drawCard(new Card(Shape.CLOVER, Letter.NINE));

        // when
        resultGame.calculateResult();

        // then
        assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.WIN);
    }

    @Test
    @DisplayName("버스트된 딜러와 버스트 안된 참가자의 게임 결과를 입력하는 테스트")
    void calculateResultWithBustedDealerTest() {

        dealer.drawCard(new Card(Shape.CLOVER, Letter.TEN));
        dealer.drawCard(new Card(Shape.DIAMOND, Letter.TEN));
        dealer.drawCard(new Card(Shape.HEART, Letter.TWO));

        Player player = participants.getPlayers().get(0);
        player.drawCard(new Card(Shape.CLOVER, Letter.NINE));
        ResultGame resultGame = new ResultGame(participants);
        resultGame.calculateResult();

        assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.WIN);
    }

    @Test
    @DisplayName("버스트된 딜러와 버스트 된 참가자의 게임 결과를 입력하는 테스트")
    void calculateResultWithBustedDealerPlayerTest() {

        dealer.drawCard(new Card(Shape.CLOVER, Letter.TEN));
        dealer.drawCard(new Card(Shape.DIAMOND, Letter.TEN));
        dealer.drawCard(new Card(Shape.HEART, Letter.TWO));

        Player player = participants.getPlayers().get(0);
        player.drawCard(new Card(Shape.CLOVER, Letter.TWO));
        player.drawCard(new Card(Shape.CLOVER, Letter.JACK));
        player.drawCard(new Card(Shape.DIAMOND, Letter.QUEEN));
        ResultGame resultGame = new ResultGame(participants);
        resultGame.calculateResult();

        assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.TIE);
    }

    @Test
    @DisplayName("딜러와 버스트 된 참가자의 게임 결과를 입력하는 테스트")
    void calculateResultWithNonBustedDealerAndBustedPlayerTest() {

        dealer.drawCard(new Card(Shape.CLOVER, Letter.TEN));
        dealer.drawCard(new Card(Shape.DIAMOND, Letter.ACE));

        Player player = participants.getPlayers().get(0);
        player.drawCard(new Card(Shape.CLOVER, Letter.TWO));
        player.drawCard(new Card(Shape.CLOVER, Letter.JACK));
        player.drawCard(new Card(Shape.DIAMOND, Letter.QUEEN));
        ResultGame resultGame = new ResultGame(participants);
        resultGame.calculateResult();

        assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.LOSE);
    }

    @Test
    @DisplayName("버스트 안된 딜러와 참가자 중 딜러가 이기는 경우 테스트")
    void calculateResultWithNonBustedDealerWinNonBustedPlayerTest() {

        dealer.drawCard(new Card(Shape.CLOVER, Letter.TEN));
        dealer.drawCard(new Card(Shape.DIAMOND, Letter.ACE));

        Player player = participants.getPlayers().get(0);
        player.drawCard(new Card(Shape.CLOVER, Letter.JACK));
        player.drawCard(new Card(Shape.DIAMOND, Letter.QUEEN));
        ResultGame resultGame = new ResultGame(participants);
        resultGame.calculateResult();

        assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.LOSE);
    }

    @Test
    @DisplayName("버스트 안된 딜러와 참가자 중 참가자가 이기는 경우 테스트")
    void calculateResultWithNonBustedDealerLoseNonBustedPlayerTest() {

        dealer.drawCard(new Card(Shape.CLOVER, Letter.TEN));
        dealer.drawCard(new Card(Shape.DIAMOND, Letter.JACK));

        Player player = participants.getPlayers().get(0);
        player.drawCard(new Card(Shape.CLOVER, Letter.JACK));
        player.drawCard(new Card(Shape.DIAMOND, Letter.ACE));
        ResultGame resultGame = new ResultGame(participants);
        resultGame.calculateResult();

        assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.WIN);
    }

    @Test
    @DisplayName("버스트 안된 딜러와 참가자 중 비기는 경우 테스트")
    void calculateResultWithNonBustedDealerTieNonBustedPlayerTest() {

        dealer.drawCard(new Card(Shape.CLOVER, Letter.ACE));
        dealer.drawCard(new Card(Shape.DIAMOND, Letter.JACK));

        Player player = participants.getPlayers().get(0);
        player.drawCard(new Card(Shape.CLOVER, Letter.JACK));
        player.drawCard(new Card(Shape.DIAMOND, Letter.ACE));
        ResultGame resultGame = new ResultGame(participants);
        resultGame.calculateResult();

        assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.TIE);
    }

    @Test
    @DisplayName("플레이어 해쉬맵을 가져오는 테스트")
    void getPlayersResultTest() {
        ResultGame resultGame = new ResultGame(participants);
        resultGame.calculateResult();

        Assertions.assertThat(resultGame.getPlayersResult().keySet())
                .contains(participants.getPlayers().get(0)
                        , participants.getPlayers().get(1));
    }
}
