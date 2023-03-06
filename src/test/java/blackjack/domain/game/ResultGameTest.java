package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Letter;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
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
    private Map<Player, WinTieLose> playersResult;


    @BeforeEach
    void setting() {
        dealer = new Dealer();
        participants = new Participants(dealer, List.of("pobi", "crong", "dali"));
        playersResult = new HashMap<>();
    }

    @Test
    @DisplayName("생성자 테스트")
    void constructorTest() {
        assertThatNoException().isThrownBy(() -> new ResultGame(participants, playersResult));
    }

    @Test
    @DisplayName("결과를 계산하는 딜러가 승리하는 테스트")
    void calculateResultDealerWinTest() {
        // given
        ResultGame resultGame = new ResultGame(participants, playersResult);
        dealer.drawCard(new Card(Shape.CLOVER, Letter.TEN));
        participants.getPlayers().get(0).drawCard(new Card(Shape.CLOVER, Letter.NINE));

        // when
        resultGame.calculateResult();

        // then
        assertThat(resultGame.getDealerCount(WinTieLose.WIN)).isEqualTo(3);
    }

    @Test
    @DisplayName("결과를 계산하는 딜러가 비기는 테스트")
    void calculateResultTieTest() {
        // given
        ResultGame resultGame = new ResultGame(participants, playersResult);
        dealer.drawCard(new Card(Shape.CLOVER, Letter.TEN));
        participants.getPlayers().get(0).drawCard(new Card(Shape.CLOVER, Letter.JACK));

        // when
        resultGame.calculateResult();

        // then
        assertThat(resultGame.getDealerCount(WinTieLose.TIE)).isEqualTo(1);
    }

    @Test
    @DisplayName("결과를 계산하는 딜러가 지는 테스트")
    void calculateResultDealerLoseTest() {
        // given
        ResultGame resultGame = new ResultGame(participants, playersResult);
        dealer.drawCard(new Card(Shape.CLOVER, Letter.EIGHT));
        participants.getPlayers().get(0).drawCard(new Card(Shape.CLOVER, Letter.NINE));

        // when
        resultGame.calculateResult();

        // then
        assertThat(resultGame.getDealerCount(WinTieLose.LOSE)).isEqualTo(1);
    }


    @Test
    @DisplayName("딜러가 버스트일 때, 딜러가 지는 테스트")
    void calculateResultDealerLoseWithBustTest() {
        // given
        ResultGame resultGame = new ResultGame(participants, playersResult);
        dealer.drawCard(new Card(Shape.CLOVER, Letter.EIGHT));
        dealer.drawCard(new Card(Shape.DIAMOND, Letter.TEN));
        dealer.drawCard(new Card(Shape.HEART, Letter.TEN));
        participants.getPlayers().get(0).drawCard(new Card(Shape.CLOVER, Letter.NINE));

        // when
        resultGame.calculateResult();

        // then
        assertThat(resultGame.getDealerCount(WinTieLose.LOSE)).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어의 결과를 출력하는 테스트")
    void getPlayerResultTest() {
        // given
        ResultGame resultGame = new ResultGame(participants, playersResult);
        dealer.drawCard(new Card(Shape.CLOVER, Letter.EIGHT));
        Player player = participants.getPlayers().get(0);
        player.drawCard(new Card(Shape.CLOVER, Letter.NINE));

        // when
        resultGame.calculateResult();

        // then
        assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.WIN);
    }
}
