package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardSuit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class ResultTest {
    private Player player1;
    private Player player2;
    private Player player3;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player1 = new Player("joel");
        player2 = new Player("bada");
        player3 = new Player("j.on");
        dealer = new Dealer();
    }

    @Test
    @DisplayName("결과 객체가 잘 생성되는지 확인")
    void constructorTest() {
        final List<Player> players = Arrays.asList(player1, player2, player3);
        assertThatCode(() -> new Result(players, dealer))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러의 승/무/패 횟수를 정상적으로 반환하는지 확인")
    void dealerResultTest() {
        //Given
        player1.receiveAdditionalCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
        player1.receiveAdditionalCard(new Card(CardLetter.JACK, CardSuit.CLOVER));

        player2.receiveAdditionalCard(new Card(CardLetter.EIGHT, CardSuit.HEART));
        player2.receiveAdditionalCard(new Card(CardLetter.NINE, CardSuit.HEART));

        player3.receiveAdditionalCard(new Card(CardLetter.TWO, CardSuit.DIAMOND));
        player3.receiveAdditionalCard(new Card(CardLetter.THREE, CardSuit.DIAMOND));

        dealer.receiveAdditionalCard(new Card(CardLetter.EIGHT, CardSuit.SPADE));
        dealer.receiveAdditionalCard(new Card(CardLetter.NINE, CardSuit.SPADE));
        //When
        final Result result = new Result(Arrays.asList(player1, player2, player3), dealer);
        //Then
        final Map<ResultEnum, Integer> dealerResult = result.checkDealerResult();
        assertThat(dealerResult.getOrDefault(ResultEnum.WIN, 0)).isEqualTo(1);
        assertThat(dealerResult.getOrDefault(ResultEnum.DRAW, 0)).isEqualTo(1);
        assertThat(dealerResult.getOrDefault(ResultEnum.LOSE, 0)).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어의 승/무/패 현황을 정상적으로 반환하는지 확인")
    void playerResultTest() {
        //Given
        player1.receiveAdditionalCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
        player1.receiveAdditionalCard(new Card(CardLetter.JACK, CardSuit.CLOVER));

        player2.receiveAdditionalCard(new Card(CardLetter.EIGHT, CardSuit.HEART));
        player2.receiveAdditionalCard(new Card(CardLetter.NINE, CardSuit.HEART));

        player3.receiveAdditionalCard(new Card(CardLetter.TWO, CardSuit.DIAMOND));
        player3.receiveAdditionalCard(new Card(CardLetter.THREE, CardSuit.DIAMOND));

        dealer.receiveAdditionalCard(new Card(CardLetter.EIGHT, CardSuit.SPADE));
        dealer.receiveAdditionalCard(new Card(CardLetter.NINE, CardSuit.SPADE));
        //When
        final Result result = new Result(Arrays.asList(player1, player2, player3), dealer);
        //Then
        final Map<Player, ResultEnum> playerResult = result.checkPlayerResult();
        assertThat(playerResult.get(player1)).isEqualTo(ResultEnum.WIN);
        assertThat(playerResult.get(player2)).isEqualTo(ResultEnum.DRAW);
        assertThat(playerResult.get(player3)).isEqualTo(ResultEnum.LOSE);
    }
}
