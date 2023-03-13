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

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class ResultGameTest {
    private Dealer dealer;
    private Participants participants;


    @BeforeEach
    void setting() {
        dealer = new Dealer();
        Map<String, Integer> players = new LinkedHashMap<>();
        players.put("pobi", 1000);
        players.put("crong", 1000);
        players.put("dali", 1000);
        participants = new Participants(dealer, players);
    }

    @Test
    @DisplayName("생성자 테스트")
    void constructorTest() {
        assertThatNoException().isThrownBy(() -> new ResultGame(participants));
    }


    @Test
    @DisplayName("참가자가 이긴경우")
    void calculateResultWithBustedDealerTest() {

        dealer.drawCard(new Card(Shape.CLOVER, Letter.TEN));
        dealer.drawCard(new Card(Shape.DIAMOND, Letter.TEN));
        dealer.drawCard(new Card(Shape.HEART, Letter.TWO));

        Player player = participants.getPlayers().get(0);
        player.drawCard(new Card(Shape.CLOVER, Letter.NINE));
        ResultGame resultGame = new ResultGame(participants);
        resultGame.calculateResult();

        assertThat(participants.getPlayers().get(0).getRevenue()).isEqualTo(1000);
    }

    @Test
    @DisplayName("딜러와 비기는 경우")
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
        assertThat(participants.getPlayers().get(0).getRevenue()).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러가 이기는 경우")
    void calculateResultWithNonBustedDealerAndBustedPlayerTest() {

        dealer.drawCard(new Card(Shape.CLOVER, Letter.TEN));
        dealer.drawCard(new Card(Shape.DIAMOND, Letter.ACE));

        Player player = participants.getPlayers().get(0);
        player.drawCard(new Card(Shape.CLOVER, Letter.TWO));
        player.drawCard(new Card(Shape.CLOVER, Letter.JACK));
        player.drawCard(new Card(Shape.DIAMOND, Letter.QUEEN));
        ResultGame resultGame = new ResultGame(participants);
        resultGame.calculateResult();
        assertThat(participants.getPlayers().get(0).getRevenue()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("딜러와 플레이어 둘다 블랙잭인 경우")
    void calculateResultWithNonBustedDealerWinNonBustedPlayerTest() {

        dealer.drawCard(new Card(Shape.CLOVER, Letter.TEN));
        dealer.drawCard(new Card(Shape.DIAMOND, Letter.ACE));

        Player player = participants.getPlayers().get(0);
        player.drawCard(new Card(Shape.CLOVER, Letter.ACE));
        player.drawCard(new Card(Shape.DIAMOND, Letter.QUEEN));
        ResultGame resultGame = new ResultGame(participants);
        resultGame.calculateResult();
        assertThat(participants.getPlayers().get(0).getRevenue()).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러가 블랙젝인 경우 플레이어는 3장 합 21인 경우")
    void calculateResultWithNonBustedDealerLoseNonBustedPlayerTest() {

        dealer.drawCard(new Card(Shape.CLOVER, Letter.ACE));
        dealer.drawCard(new Card(Shape.DIAMOND, Letter.JACK));

        Player player = participants.getPlayers().get(0);
        player.drawCard(new Card(Shape.CLOVER, Letter.JACK));
        player.drawCard(new Card(Shape.DIAMOND, Letter.TEN));
        player.drawCard(new Card(Shape.HEART, Letter.ACE));
        ResultGame resultGame = new ResultGame(participants);
        resultGame.calculateResult();

        assertThat(participants.getPlayers().get(0).getRevenue()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭 딜러가 3잡 합 21인 경우")
    void calculateResultWithNonBustedDealerTieNonBustedPlayerTest() {
        Player player = participants.getPlayers().get(0);
        player.drawCard(new Card(Shape.CLOVER, Letter.ACE));
        player.drawCard(new Card(Shape.DIAMOND, Letter.JACK));

        dealer.drawCard(new Card(Shape.CLOVER, Letter.JACK));
        dealer.drawCard(new Card(Shape.DIAMOND, Letter.TEN));
        dealer.drawCard(new Card(Shape.HEART, Letter.ACE));
        ResultGame resultGame = new ResultGame(participants);
        resultGame.calculateResult();

        assertThat(participants.getPlayers().get(0).getRevenue()).isEqualTo(1500);
    }
}
